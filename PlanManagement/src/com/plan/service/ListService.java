package com.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.plan.dao.ListDao;

@Component
public class ListService {

	@Autowired
	ListDao listDao;

	public boolean addList(String user_id, String list_name, String remark) {
		// TODO Auto-generated method stub
		return listDao.addList(user_id,list_name,remark);
	}

	public boolean deleteList(String list_id) {
		// TODO Auto-generated method stub
		return listDao.deleteList(list_id);
	}

	public boolean updateList(String list_id,String list_name, String remark) {
		// TODO Auto-generated method stub
		return listDao.updateList(list_id,list_name,remark);
	}
	
	public List<Map<String, Object>> getList(String user_id) {
		// TODO Auto-generated method stub
		return listDao.getList(user_id);
	}
	
	public Map<String, Object> getListInfo(String list_id) {
		// TODO Auto-generated method stub
		return listDao.getListInfo(list_id);
	}
}
