package com.blog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.blog.dao.ArticleTypeDAO;

@Component
public class ArticleTypeService {
	@Autowired
	ArticleTypeDAO articleTypeDAO;


	public List<Map<String,Object>> getArticleType() {
		// TODO Auto-generated method stub
		return articleTypeDAO.getArticleType();
	}
}
