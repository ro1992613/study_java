package com.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.common.utils.StringUtil;

@Component
public class UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<String, Object> getUserInfo(String user_id) {
		String sql="select * from sys_user where user_id=?";
		Map<String, Object> map=null;
		try {
			map=jdbcTemplate.queryForMap(sql,user_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	public boolean addUser(String user_id, String user_name, String user_pass, String user_alias, String remark) {
		boolean rs=false;
		String sql="insert into sys_user(user_id,user_name,user_pass,user_alias,create_time,remark) values(?,?,?,?,NOW(),?)";
		try {
			int num=jdbcTemplate.update(sql,user_id,user_name,user_pass,user_alias,remark);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public boolean deleteUser(String user_id) {
		boolean rs=false;
		String sql="delete from sys_user where user_id=?";
		try {
			int num=jdbcTemplate.update(sql,user_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public boolean updateUser(String user_id, String user_name, String user_pass, String user_alias, String remark) {
		boolean rs=false;
		String sql="update sys_user set user_name=?,user_pass=?,user_alias=?,remark=? where user_id=?";
		try {
			int num=jdbcTemplate.update(sql,user_name,user_pass,user_alias,remark,user_id);
			if(num==1){
				rs=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public List<Map<String, Object>> getAllUsers(int page_no, int page_size) {
		String sql="select user_id,user_name,user_alias,create_time,remark from sys_user where 1=1 and user_id<>(SELECT `value` FROM sys_param WHERE `key`='super_user_id')";
		sql=sql+" limit "+(page_no-1)*page_size+","+page_size;
		
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			list.addAll(jdbcTemplate.queryForList(sql));
		} catch (Exception e) {
		}
		return list;
	}

	public int getAllUsersCount() {
		int rs=0;
		String sql="select user_id from sys_user where 1=1";
		try {
			rs=rs+jdbcTemplate.queryForList(sql).size();
		} catch (Exception e) {
		}
		return rs;
	}

	public List<String> getRolesByUserId(String user_id) {
		List<String> list=new ArrayList<>();
		String sql="select role_id from sys_user_role where user_id=?";
		try {
			SqlRowSet rs=jdbcTemplate.queryForRowSet(sql,user_id);
			while(rs.next()){
				list.add(rs.getString("role_id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public boolean updateUserRoles(String user_id, String[] role_ids) {
		String delete_sql="delete from sys_user_role where user_id=?";
		String insert_sql="insert into sys_user_role(user_id,role_id) values(?,?)";
		jdbcTemplate.update(delete_sql,user_id);
		for(String role_id:role_ids){
			if(!StringUtil.IsNullOrEmpty(role_id)&&!StringUtil.IsNullOrEmpty(user_id)){
				jdbcTemplate.update(insert_sql,user_id,role_id);
			}
		}
		return true;
	}

	public boolean isUserExisted(String user_name) {
		boolean existed=false;
		String sql="select 1 from sys_user where user_name=?";
		try {
			if(jdbcTemplate.queryForMap(sql, user_name)!=null){
				existed=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return existed;
	}
}
