package com.plan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.APIResult;
import com.common.utils.Page;
import com.common.utils.StringUtil;
import com.plan.service.TaskService;
@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("task:addTask")
	@RequestMapping(value="/addTask",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> addTask(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String user_id=user.get("user_id").toString();
		String list_id=request.getParameter("list_id");
		String task_name=request.getParameter("task_name");
		String task_level=request.getParameter("task_level");
		String task_date=request.getParameter("task_date");
		String remark=request.getParameter("remark");

		if(StringUtil.IsNullOrEmpty(task_level)){
			task_level="0";
		}
		if(!StringUtil.IsNullOrEmpty(task_name)){
			if(taskService.addTask(user_id,list_id,task_name,task_level,task_date,remark)){
				result.setCode(1);
			}
		}
		return result;
	}
	
	@RequiresPermissions("task:deleteTask")
	@RequestMapping(value="/deleteTask",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> deleteTask(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		String task_id=request.getParameter("task_id");
		if(taskService.deleteTask(task_id)){
			result.setCode(1);
		}
		
		return result;
	}
	
	@RequiresPermissions("task:updateTask")
	@RequestMapping(value="/updateTask",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateTask(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		String task_id=request.getParameter("task_id");
		String list_id=request.getParameter("list_id");
		String task_name=request.getParameter("task_name");
		String task_level=request.getParameter("task_level");
		String task_state=request.getParameter("task_state");
		String remark=request.getParameter("remark");
		
		if(!StringUtil.IsNullOrEmpty(task_name)
				&&!StringUtil.IsNullOrEmpty(task_id) 
				&&!StringUtil.IsNullOrEmpty(task_level) 
				&&!StringUtil.IsNullOrEmpty(task_state)){
			if(taskService.updateTask(task_id,list_id,task_name,task_state,task_level,remark)){
				result.setCode(1);
			}
		}
		return result;
	}
	
	@RequiresPermissions("task:getTasks")
	@RequestMapping(value="/getTasks",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Page<Map<String,Object>>> getTasks(HttpServletRequest request, HttpServletResponse response){
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		String list_id=request.getParameter("list_id");
		String task_state=request.getParameter("task_state");
		String page_no=request.getParameter("page_no");
		String page_size=request.getParameter("page_size");
		
		int i_page_no=1;
		int i_page_size=10;
		try {
			i_page_size=Integer.parseInt(page_size);
			if(i_page_size<=10){
				i_page_size=10;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			i_page_no=Integer.parseInt(page_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<Map<String,Object>> tasks=taskService.getTasks(list_id,task_state,i_page_no,i_page_size);
		if(tasks==null || tasks.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(new Page<Map<String, Object>>(taskService.getTasksCount(list_id,task_state),i_page_no,i_page_size,tasks));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("task:getAllTasks")
	@RequestMapping(value="/getAllTasks",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Page<Map<String,Object>>> getAllTasks(HttpServletRequest request, HttpServletResponse response){
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		String task_state=request.getParameter("task_state");
		String page_no=request.getParameter("page_no");
		String page_size=request.getParameter("page_size");
		
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String user_id=user.get("user_id").toString();
		System.out.println(user);
		int i_page_no=1;
		int i_page_size=10;
		try {
			i_page_size=Integer.parseInt(page_size);
			if(i_page_size<=10){
				i_page_size=10;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			i_page_no=Integer.parseInt(page_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		List<Map<String,Object>> tasks=taskService.getAllTasks(user_id,task_state,i_page_no,i_page_size);
		if(tasks==null || tasks.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(new Page<Map<String, Object>>(taskService.getAllTasksCount(user_id,task_state),i_page_no,i_page_size,tasks));
		}
		return result;
	}
	@RequiresPermissions("task:getTaskInfo")
	@RequestMapping(value="/getTaskInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getTaskInfo(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		String task_id=request.getParameter("task_id");
		try {
			Map<String,Object> map=taskService.getListInfo(task_id);
			if(map!=null){
				result.setCode(1);
				result.setData(map);
			}else {
				result.setMsg("no data");
			}
		} catch (Exception e) {
			result.setMsg("server error!");
		};
		return result;
	}
	
}
