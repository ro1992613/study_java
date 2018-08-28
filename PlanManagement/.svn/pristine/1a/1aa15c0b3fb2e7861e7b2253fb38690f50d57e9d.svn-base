package com.blog.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class  ReplyDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean insertReply(Map<String, Object> reply) {
		boolean result=false;
		String sql="insert into blog_reply(reply_id,reply_ip,reply_name,reply_time,reply_text_id,"
				+ "reply_text_title,reply_user_id,reply_user_alias,reply_content) values(?,?,?,?,?,?,?,?,?)";
		try {
			int rs=jdbcTemplate.update(sql,reply.get("reply_id").toString(),reply.get("reply_ip").toString(),reply.get("reply_name").toString(),new Date()
					,reply.get("reply_text_id").toString(),reply.get("reply_text_title").toString(),reply.get("reply_user_id"),reply.get("reply_user_alias"),reply.get("reply_content").toString());
			if(rs==1){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> getReplyList(String article_id, int page_index, int page_size) {
		List<Map<String, Object>> result=null;
		String sql="select * from blog_reply where reply_text_id=? order by reply_time desc limit ?,?";
		try {
			result=jdbcTemplate.queryForList(sql,article_id,(page_index-1)*page_size,page_size);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getReplyCount(String article_id) {
		int result=0;
		String sql="select count(1) as count from blog_reply where reply_text_id=?";
		try {
			Map<String, Object> m=jdbcTemplate.queryForMap(sql,article_id);
			if(m!=null){
				result=Integer.parseInt(m.get("count").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteReply(String reply_id) {
		boolean result=false;
		String sql="delete from blog_reply where reply_id=?";
		try {
			int rs=jdbcTemplate.update(sql,reply_id);
			if(rs==1){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean deleteReplyList(String article_id) {
		boolean result=false;
		String sql="delete from blog_reply where reply_text_id=?";
		try {
			int rs=jdbcTemplate.update(sql,article_id);
			if(rs>0){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public List<Map<String, Object>> getLatestReplyList() {
		String sql="SELECT * from blog_reply ORDER BY reply_time DESC LIMIT 20";
		return jdbcTemplate.queryForList(sql);
	}
	
}
