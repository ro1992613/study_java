package com.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.utils.StringUtil;

@Component
public class PermissionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<String, Object> getPermissionInfo(String permission_id) {
		String sql="select * from sys_permission where permission_id=?";
		Map<String, Object> map=null;
		try {
			map=jdbcTemplate.queryForMap(sql,permission_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	public boolean deletePermission(String permission_id) {
		boolean rs=false;
		String sql="delete from sys_permission where permission_id=?";
		try {
			int num=jdbcTemplate.update(sql,permission_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public boolean addPermission(String permission_id,String permission_father_id, String permission_name, String permission_url,
			String permission_type) {
		boolean rs=false;
		String sql="insert into sys_permission(permission_id,permission_father_id,permission_name,permission_url,permission_type) values(?,?,?,?,?)";
		try {
			int num=jdbcTemplate.update(sql,permission_id,permission_father_id,permission_name,permission_url,permission_type);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public boolean updatePermission(String permission_id,String permission_father_id, String permission_name, String permission_url,
			String permission_type) {
		// TODO Auto-generated method stub
		boolean rs=false;
		String sql="update sys_permission set permission_father_id=?,permission_name=?,permission_url=?,permission_type=? where permission_id=?";
		try {
			int num=jdbcTemplate.update(sql,permission_father_id,permission_name,permission_url,permission_type,permission_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public List<Map<String, Object>> getAllPermissions() {
		String sql="select * from sys_permission where 1=1";		
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			list.addAll(jdbcTemplate.queryForList(sql));
		} catch (Exception e) {
		}
		return list;
	}

	public List<Map<String, Object>> getPermissionsByRoleId(String role_id) {
		String sql="select permission_id from sys_role_permission where role_id=?";
		List<Map<String, Object>> result=null;
		if(!StringUtil.IsNullOrEmpty(role_id)){
			try {
				result=jdbcTemplate.queryForList(sql,role_id);
			} catch (Exception e) {
			}
		}
		return result;
	}

	public List<Map<String, Object>> getChildPermissionId(String permission_id) {
		String sql="select permission_id from sys_permission where permission_father_id=?";
		List<Map<String, Object>> result=null;
		if(!StringUtil.IsNullOrEmpty(permission_id)){
			try {
				result=jdbcTemplate.queryForList(sql,permission_id);
			} catch (Exception e) {
			}
		}
		return result;
	}
}
