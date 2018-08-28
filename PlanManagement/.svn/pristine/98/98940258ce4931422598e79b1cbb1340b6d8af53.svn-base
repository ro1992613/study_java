package com.blog.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.utils.StringUtil;
@Component
public class ArticleDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean updateArticle(Map<String, Object> article) {
		boolean result=false;
		String sql="update blog_article set article_title=?,article_type=?,article_content=?"
				+ ",article_logo_url=?,article_summary=?,article_clicktimes=?"
				+ " where article_id=?";
		try {
			int rs=jdbcTemplate.update(sql,article.get("article_title").toString()
					,article.get("article_type").toString(),article.get("article_content").toString(),article.get("article_logo_url").toString()
					,article.get("article_summary").toString(),article.get("article_clicktimes"),article.get("article_id").toString());
			if(rs>0){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getArticleCount(String article_type, String article_author_id, String key) {
		int result=0;
		String sql="select count(1) as count from blog_article where 1=1";
		if(!StringUtil.IsNullOrEmpty(article_type)){sql=sql+" and article_type like '%"+article_type+"%'";}
		if(!StringUtil.IsNullOrEmpty(article_author_id)){sql=sql+" and article_author_id='"+article_author_id+"'";}
		if(!StringUtil.IsNullOrEmpty(key)){sql=sql+" and article_title like '%"+key+"%'";}
		try {
			Map<String, Object> m=jdbcTemplate.queryForMap(sql);
			if(m!=null){
				result=Integer.parseInt(m.get("count").toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public Map<String, Object> getArticle(String article_id) {
		Map<String, Object> result=null;
		String sql="select * from blog_article where article_id=?";
		try {
			result=jdbcTemplate.queryForMap(sql,article_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean insertArticle(Map<String, Object> article) {
		boolean result=false;
		String sql="insert into blog_article(article_id,article_title,article_type,article_content"
				+ ",article_logo_url,article_summary,article_createtime,article_clicktimes,article_replytimes"
				+ ",article_author_id,article_author_name) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			int rs=jdbcTemplate.update(sql,article.get("article_id").toString(),article.get("article_title").toString()
					,article.get("article_type").toString(),article.get("article_content").toString(),article.get("article_logo_url").toString()
					,article.get("article_summary").toString(),new Date(),0
					,0,article.get("article_author_id").toString(),article.get("article_author_name").toString());
			if(rs>0){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public List<Map<String, Object>> getArticleList(String article_type, String article_author_id, String key,
			int page_index, int page_size) {
		List<Map<String, Object>> result=null;
		String sql="select * from blog_article where 1=1";
		if(!StringUtil.IsNullOrEmpty(article_type)){sql=sql+" and article_type like '%"+article_type+"%'";}
		if(!StringUtil.IsNullOrEmpty(article_author_id)){sql=sql+" and article_author_id='"+article_author_id+"'";}
		if(!StringUtil.IsNullOrEmpty(key)){sql=sql+" and article_title like '%"+key+"%'";}
		sql= sql+" order by article_createtime desc limit ?,?";
		
		try {
			result=jdbcTemplate.queryForList(sql,(page_index-1)*page_size,page_size);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean updateArticleReplytimes(String article_id) {
		boolean result=false;
		String sql="update blog_article ba set ba.article_replytimes=(SELECT COUNT(1) FROM blog_reply br WHERE br.reply_text_id=?)+1 where ba.article_id=?";
		try {
			int rs=jdbcTemplate.update(sql,article_id,article_id);
			if(rs>0){
				result=true;
			}
		} catch (Exception e) {
		}
		return result;
	}

	public boolean deleteArticle(String article_id) {
		boolean result=false;
		String sql="delete from blog_article where article_id=?";
		String sql_delreply="delete from blog_reply where reply_text_id=?";
		try {
			int rs=jdbcTemplate.update(sql,article_id);
			if(rs>0){
				result=true;
			}
			jdbcTemplate.update(sql_delreply,article_id);
		} catch (Exception e) {
		}
		return result;
	}

	public List<Map<String, Object>> getHottestArticleList() {
		String sql="SELECT * FROM (SELECT * FROM blog_article ORDER BY article_createtime DESC LIMIT 20) ba ORDER BY ba.article_replytimes DESC,ba.article_clicktimes DESC LIMIT 8";
		return jdbcTemplate.queryForList(sql);
	}


}
