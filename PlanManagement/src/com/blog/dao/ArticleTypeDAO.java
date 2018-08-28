package com.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class ArticleTypeDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Map<String,Object>> getArticleType() {
		String sql="select type from blog_type order by id desc";
		
		return jdbcTemplate.queryForList(sql);
	}
}
