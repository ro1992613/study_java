package com.plan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.APIResult;
import com.common.utils.StringUtil;
import com.plan.service.ContentService;
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	ContentService contentService;	
	@RequiresPermissions("content:updateContent")
	@RequestMapping(value="/updateContent",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateContent(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String task_id=request.getParameter("task_id");
		String content=request.getParameter("content");
		if(!StringUtil.IsNullOrEmpty(task_id)){
			if(contentService.updateContent(task_id,content)){
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("content:getContent")
	@RequestMapping(value="/getContent",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getContent(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		
		String task_id=request.getParameter("task_id");
		try {
			Map<String,Object> temp=contentService.getContent(task_id);
			if(temp!=null){
				result.setCode(1);
				result.setData(temp);
			}else {
				result.setMsg("no data");
			}
		} catch (Exception e) {
			result.setMsg("server error!");
		};
		return result;
	} 
}
