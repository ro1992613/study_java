package com.plan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.utils.StringUtil;
@Component
public class ListDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean addList(String user_id, String list_name, String remark) {
		String sql="insert into pl_list(list_id,user_id,list_name,remark) values(?,?,?,?)";
		boolean result=false;
		try {
			jdbcTemplate.update(sql,"list_"+System.nanoTime(), user_id,list_name,remark);
			result=true;
		} catch (Exception e) {
		}
		return result;
	}

	public boolean deleteList(String list_id) {
		String list_sql="delete from pl_list where list_id=?";
		String content_sql="delete from pl_content where task_id in (select task_id from pl_task where list_id=?)";
		String task_sql="delete from pl_task where list_id=?";
		boolean result=false;
		try {
			jdbcTemplate.update(content_sql, list_id);
			jdbcTemplate.update(task_sql, list_id);
			jdbcTemplate.update(list_sql, list_id);
			result=true;
		} catch (Exception e) {
		}
		return result;
	}

	public boolean updateList(String list_id,String list_name, String remark) {
		String sql="update pl_list set list_name=?,remark=? where list_id=?";
		boolean result=false;
		try {
			jdbcTemplate.update(sql,list_name,remark,list_id);
			result=true;
		} catch (Exception e) {
		}
		return result;
	}

	public List<Map<String, Object>> getList(String user_id) {
		String sql="select * from pl_list where user_id=?";
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			list.addAll(jdbcTemplate.queryForList(sql,user_id));
		} catch (Exception e) {
		}
		return list;
	}

	public Map<String, Object> getListInfo(String list_id) {
		String sql="select * from pl_list where list_id=?";
		Map<String, Object> map=null;
		try {
			if(!StringUtil.IsNullOrEmpty(list_id)){
				map=jdbcTemplate.queryForMap(sql,list_id);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
