package com.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.plan.dao.TaskDao;

@Component
public class TaskService {

	@Autowired
	TaskDao taskDao;

	public boolean addTask(String user_id,String list_id, String task_name, String task_level,String task_date, String remark) {
		// TODO Auto-generated method stub
		return taskDao.addTask(user_id,list_id,task_name,task_level,task_date,remark);
	}

	public boolean deleteTask(String task_id) {
		// TODO Auto-generated method stub
		return taskDao.deleteTask(task_id);
	}

	public boolean updateTask(String task_id,String list_id, String task_name, String task_state,String task_level, String remark) {
		// TODO Auto-generated method stub
		return taskDao.updateTask(task_id,list_id,task_name,task_state,task_level,remark);
	}
	
	public List<String> getListIdByUserId(String user_id) {
		// TODO Auto-generated method stub
		return taskDao.getListIdByUserId(user_id);
	}
	
	public List<Map<String, Object>> getTasks(String list_id, String task_state, int page_no, int page_size) {
		// TODO Auto-generated method stub
		return taskDao.getTasks(list_id,task_state,page_no,page_size);
	}
	
	public int getTasksCount(String list_id, String task_state) {
		// TODO Auto-generated method stub
		return taskDao.getTasksCount(list_id,task_state);
	}
	
	public List<Map<String, Object>> getAllTasks(String user_id, String task_state, int page_no, int page_size) {
		// TODO Auto-generated method stub
		return taskDao.getAllTasks(user_id,task_state,page_no,page_size);
	}
	
	public int getAllTasksCount(String user_id, String task_state) {
		// TODO Auto-generated method stub
		return taskDao.getAllTasksCount(user_id,task_state);
	}
	
	public Map<String, Object> getListInfo(String task_id) {
		// TODO Auto-generated method stub
		return taskDao.getListInfo(task_id);
	}
}
