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
import com.common.utils.StringUtil;
import com.plan.service.ListService;
@Controller
@RequestMapping("/list")
public class ListController {

	@Autowired
	ListService listService;
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("list:addList")
	@RequestMapping(value="/addList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> addList(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String user_id=user.get("user_id").toString();
		
		//String user_id="1";
		String list_name=request.getParameter("list_name");
		String remark=request.getParameter("remark");
		if(!StringUtil.IsNullOrEmpty(list_name) && !StringUtil.IsNullOrEmpty(user_id)){
			if(listService.addList(user_id,list_name,remark)){
				result.setCode(1);
			}
		}
		return result;
	}
	
	@RequiresPermissions("list:deleteList")
	@RequestMapping(value="/deleteList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> deleteList(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		String list_id=request.getParameter("list_id");
		if(listService.deleteList(list_id)){
			result.setCode(1);
		}
		
		return result;
	}
	@RequiresPermissions("list:updateList")
	@RequestMapping(value="/updateList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateList(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		String list_name=request.getParameter("list_name");
		String remark=request.getParameter("remark");
		String list_id=request.getParameter("list_id");
		if(!StringUtil.IsNullOrEmpty(list_name)&& !StringUtil.IsNullOrEmpty(list_id)){
			if(listService.updateList(list_id,list_name,remark)){
				result.setCode(1);
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("list:getList")
	@RequestMapping(value="/getList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<Map<String,Object>>> getList(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String user_id=user.get("user_id").toString();
		try {
			List<Map<String,Object>> list=listService.getList(user_id);
			if(list!=null && list.size()>0){
				result.setCode(1);
				result.setData(list);
			}else {
				result.setMsg("no data");
			}
		} catch (Exception e) {
			result.setMsg("server error!");
		};
		return result;
	}
	@RequiresPermissions("list:getListInfo")
	@RequestMapping(value="/getListInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getListInfo(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		String list_id=request.getParameter("list_id");
		try {
			Map<String,Object> map=listService.getListInfo(list_id);
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
