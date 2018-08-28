package com.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.blog.dao.ArticleDAO;
@Component
public class ArticleService {

	@Autowired
	ArticleDAO articleDAO;
	
	public boolean updateArticle(Map<String, Object> article){
		return articleDAO.updateArticle(article);
	}
	
	public int getArticleCount(String article_type,String article_author_id,String key){
		return articleDAO.getArticleCount(article_type,article_author_id,key);
	}
	@Cacheable(value="textCache")
	public Map<String, Object> getArticle(String article_id){
		return articleDAO.getArticle(article_id);
	}
	public boolean insertArticle(Map<String, Object> article) {
		return articleDAO.insertArticle(article);
	}
	
	public List<Map<String, Object>> getArticleList(String article_type, String article_author_id, String key,
			int page_index, int page_size) {
		return articleDAO.getArticleList(article_type,article_author_id,key,page_index,page_size);
	}
	public boolean deleteArticle(String article_id) {
		// TODO Auto-generated method stub
		return articleDAO.deleteArticle(article_id);
	}
	public boolean updateArticleReplytimes(String article_id) {
		return articleDAO.updateArticleReplytimes(article_id);
	}

	public List<Map<String, Object>> getHottestArticleList() {
		// TODO Auto-generated method stub
		return articleDAO.getHottestArticleList();
	}

}
