package com.common.controller;

import java.util.List;
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
import com.common.service.UserService;
import com.common.utils.MD5Util;
import com.common.utils.Page;
import com.common.utils.StringUtil;
@Controller
@RequestMapping("/rightmanage/user")
public class UserController {
	@Autowired
	UserService userService;
	@RequiresPermissions("rightmanage:user:getUserInfo")
	@RequestMapping(value="/getUserInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getUserInfo(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		String user_id=request.getParameter("user_id");
		if(!StringUtil.IsNullOrEmpty(user_id)){
			Map<String,Object> map=userService.getUserInfo(user_id);
			if(map!=null){
				result.setData(map);
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:addUser")
	@RequestMapping(value="/addUser",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> addUser(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_id="user_"+StringUtil.getID();
		String user_name=request.getParameter("user_name");
		String user_pass=request.getParameter("user_pass");
		String user_alias=request.getParameter("user_alias");
		String remark=request.getParameter("remark");
		if(!StringUtil.IsNullOrEmpty(user_name)
				&&!StringUtil.IsNullOrEmpty(user_pass)
				&&!StringUtil.IsNullOrEmpty(user_alias)){
			user_pass=MD5Util.encode(user_pass);
			boolean rs=userService.addUser(user_id,user_name,user_pass,user_alias,remark);
			if(rs){
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:deleteUser")
	@RequestMapping(value="/deleteUser",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> deleteUser(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_id=request.getParameter("user_id");
		if(!StringUtil.IsNullOrEmpty(user_id)){
			boolean rs=userService.deleteUser(user_id);
			if(rs){
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:updateUser")
	@RequestMapping(value="/updateUser",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateUser(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_id=request.getParameter("user_id");
		String user_name=request.getParameter("user_name");
		String user_pass=request.getParameter("user_pass");
		String user_alias=request.getParameter("user_alias");
		String remark=request.getParameter("remark");
		if(!StringUtil.IsNullOrEmpty(user_name)
				&&!StringUtil.IsNullOrEmpty(user_id)
				&&!StringUtil.IsNullOrEmpty(user_pass)
				&&!StringUtil.IsNullOrEmpty(user_alias)){
			user_pass=MD5Util.encode(user_pass);
			boolean rs=userService.updateUser(user_id,user_name,user_pass,user_alias,remark);
			if(rs){
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:getAllUsers")
	@RequestMapping(value="/getAllUsers",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Page<Map<String,Object>>> getAllUsers(HttpServletRequest request, HttpServletResponse response){
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		String page_no=request.getParameter("page_no");
		String page_size=request.getParameter("page_size");
		int i_page_no=1;
		int i_page_size=20;
		try {
			i_page_size=Integer.parseInt(page_size);
			if(i_page_size<=20){
				i_page_size=20;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			i_page_no=Integer.parseInt(page_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<Map<String,Object>> users=userService.getAllUsers(i_page_no,i_page_size);
		if(users==null || users.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(new Page<Map<String, Object>>(userService.getAllUsersCount(),i_page_no,i_page_size,users));
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:getRolesByUserId")
	@RequestMapping(value="/getRolesByUserId",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<String>> getRolesByUserId(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<String>> result=new APIResult<>();
		String user_id=request.getParameter("user_id");
		
		List<String> roles=null;
		if(!StringUtil.IsNullOrEmpty(user_id)){
			roles=userService.getRolesByUserId(user_id);
		}
		if(roles==null || roles.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(roles);
		}
		return result;
	}
	@RequiresPermissions("rightmanage:user:updateUserRoles")
	@RequestMapping(value="/updateUserRoles",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateUserRoles(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_id=request.getParameter("user_id");
		String role_id=request.getParameter("role_id");
		String[] role_ids={};
		if(!StringUtil.IsNullOrEmpty(role_id)){
			role_ids=role_id.split(",");
		}
		boolean rs=userService.updateUserRoles(user_id,role_ids);
		if(rs){
			result.setCode(1);
		}
		return result;
	}
}
