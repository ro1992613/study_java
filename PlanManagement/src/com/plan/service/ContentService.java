package com.plan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.plan.dao.ContentDao;

@Component
public class ContentService {

	@Autowired
	ContentDao contentDao;

	public boolean updateContent(String task_id, String content) {
		// TODO Auto-generated method stub
		return contentDao.updateContent(task_id,content);
	}
	public Map<String, Object> getContent(String task_id) {
		// TODO Auto-generated method stub
		return contentDao.getContent(task_id);
	}
}
