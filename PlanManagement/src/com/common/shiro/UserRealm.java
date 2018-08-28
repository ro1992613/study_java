package com.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**

 * 认证

 * 

 * @author chenshun

 * @email sunlightcs@gmail.com

 * @date 2016年11月10日 上午11:55:49

 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserRealm() {
		super();
	}

	/**

	 * 认证(登录时调用)

	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("AuthenticationInfo");
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        Map<String,Object> user=jdbcTemplate.queryForMap("select * from sys_user where user_name='"+username+"'");
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        
        //密码错误
        if(!password.equals(user.get("user_pass"))) {
        	throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        System.out.println(user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Map<String,Object> user = (Map<String,Object>)principals.getPrimaryPrincipal();
		String user_id = user.get("user_id").toString();
		
		String admin_id=jdbcTemplate.queryForMap("select `value` from sys_param where `key`='super_user_id'").get("value").toString();
		
		List<Map<String,Object>> permsList;
		if(user_id.equals(admin_id)){
			String sql="select * from sys_permission";
			permsList = jdbcTemplate.queryForList(sql);
		}else{
			String  sql="SELECT sp.* FROM sys_role_permission srp "
					+ "INNER JOIN (SELECT DISTINCT role_id,user_id FROM sys_user_role) role ON role.role_id=srp.role_id "
					+ "LEFT JOIN sys_permission sp ON sp.permission_id=srp.permission_id WHERE role.user_id=?";
			permsList = jdbcTemplate.queryForList(sql,user_id);
		}
		Set<String> permsSet = new HashSet<String>();
		for(Map<String,Object> perms : permsList){
			if(perms.get("permission_url")!=null){
				permsSet.add(perms.get("permission_url").toString());
			}
		}

		Set<String> role = new HashSet<String>();
		role.add("root");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(role);
		info.setStringPermissions(permsSet);
		return info;
	}

}