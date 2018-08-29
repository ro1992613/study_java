package com.silence.sample.dao;

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
}
