package com.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.utils.StringUtil;

@Component
public class RoleDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean addRole(String role_id, String role_name, String remark) {
		boolean rs=false;
		String sql="insert into sys_role(role_id,role_name,remark) values(?,?,?)";
		try {
			int num=jdbcTemplate.update(sql,role_id,role_name,remark);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public boolean deleteRole(String role_id) {
		boolean rs=false;
		String sql="delete from sys_role where role_id=?";
		try {
			int num=jdbcTemplate.update(sql,role_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public Map<String, Object> getRoloInfo(String role_id) {
		String sql="select * from sys_role where role_id=?";
		Map<String, Object> map=null;
		try {
			map=jdbcTemplate.queryForMap(sql,role_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	public boolean updateRole(String role_id, String role_name, String remark) {
		boolean rs=false;
		String sql="update sys_role set role_name=?,remark=? where role_id=?";
		try {
			int num=jdbcTemplate.update(sql,role_name,remark,role_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public List<Map<String, Object>> getAllRoles() {
		String sql="select * from sys_role where 1=1";		
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			list.addAll(jdbcTemplate.queryForList(sql));
		} catch (Exception e) {
		}
		return list;
	}

	public boolean updateRolePermissions(String role_id, String[] permission_ids) {
		String delete_sql="delete from sys_role_permission where role_id=?";
		String insert_sql="insert into sys_role_permission(role_id,permission_id) values(?,?)";
		jdbcTemplate.update(delete_sql,role_id);
		for(String permission_id:permission_ids){
			if(!StringUtil.IsNullOrEmpty(role_id)&&!StringUtil.IsNullOrEmpty(permission_id)){
				jdbcTemplate.update(insert_sql,role_id,permission_id);
			}
		}
		return true;
	}
}
