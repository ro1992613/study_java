package com.plan.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class ContentDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean updateContent(String task_id, String content) {
		String sql="update pl_content set content=? where task_id=?";
		boolean rs=false;
		try {
			if(jdbcTemplate.update(sql, content,task_id)==1){
				rs=true;
			}
		} catch (Exception e) {
		}
		return rs;
	}

	public Map<String, Object> getContent(String task_id) {
		Map<String, Object> rs=new HashMap<>();
		String sql="select * from pl_content where task_id=?";
		try {
			rs=jdbcTemplate.queryForMap(sql, task_id);
		} catch (Exception e) {
		}
		return rs;
	}
}
