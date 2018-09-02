package com.ssm.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> getSamples() {
		List<Map<String, Object>> result=null;
		String sql="select * from test";
		try {
			result=jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public void insertTest() {
		String sql="insert into article(article_id) values("+System.currentTimeMillis()+")";
		jdbcTemplate.update(sql);
	}
	
	public void insertTest1() {
		String sql="insert into article(article_id) values("+System.currentTimeMillis()+"aa"+")";
		jdbcTemplate.update(sql);
	}
	
	public void findTest() {
		String sql="select * from article";
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		for(Map<String,Object> map:list) {
			System.out.println(map.get("article_id"));
		}
	}
}
