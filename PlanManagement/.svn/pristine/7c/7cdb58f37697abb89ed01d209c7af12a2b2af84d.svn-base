package com.blog.dao;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class ImgDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<String> getImgList(int page) {
		List<String> list=new ArrayList<>();
		String sql="select * from jandan_pic where page=?";
		SqlRowSet rs=jdbcTemplate.queryForRowSet(sql,page);
		while(rs.next()){
			list.add(rs.getString("pic"));
		}
		return list;
	}
	
	public int getMaxPage(){
		int page=320;
		String sql="select MAX(page) page from jandan_pic";
		SqlRowSet rs=jdbcTemplate.queryForRowSet(sql);
		if(rs.next()){
			page=rs.getInt("page");
		}
		return page;
	};
}
