package com.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.APIResult;
import com.common.service.UserService;
import com.common.utils.MD5Util;
import com.common.utils.StringUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> login(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_name=request.getParameter("user_name");
		String user_pass=request.getParameter("user_pass");  
		UsernamePasswordToken token = new UsernamePasswordToken(user_name, MD5Util.encode(user_pass));    
        Subject currentUser = SecurityUtils.getSubject();   
        try {
        	currentUser.login(token);
        	result.setData("登录成功");
        	result.setCode(1);
		} catch (UnknownAccountException e) {
			result.setData("账号不存在");
		} catch (IncorrectCredentialsException e) {
			result.setData("账号或密码不正确");
		} 
        
        return result; 
	}
	
	@RequestMapping(value="/register",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> register(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String user_id="user_"+StringUtil.getID();
		String user_name=request.getParameter("user_name");
		String user_pass=request.getParameter("user_pass"); 
		String user_alias=request.getParameter("user_alias"); 
		boolean existed=userService.isUserExisted(user_name);
		if(!StringUtil.IsNullOrEmpty(user_name) &&
				!StringUtil.IsNullOrEmpty(user_pass)&&
				!StringUtil.IsNullOrEmpty(user_alias)){
			if(!existed){
				boolean rs=userService.addUser(user_id, user_name.trim(), MD5Util.encode(user_pass.trim()), user_alias.trim(), null);
				if(rs){
					result.setCode(1);
				}
			}else{
				result.setData("用户已经存在！");
			}
		}else{
			result.setData("输入的信息不完整！");
		}
        return result; 
	}
	
	@RequestMapping(value="/logout",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> logout(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(1);
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
        return result; 
	}
	
	@RequestMapping(value="/check_login_status",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> checkLoginStatus(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		Subject subject = SecurityUtils.getSubject();   
        if(subject.isAuthenticated()){
        	result.setCode(1);
        }
        return result; 
	}
	
	@RequestMapping(value="/get_login_info",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> get_login_info(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		Subject subject = SecurityUtils.getSubject();   
        if(subject.isAuthenticated()){
        	result.setCode(1);
        	Map<String,Object> map=(Map<String, Object>) subject.getPrincipal();
        	map.put("user_pass", null);
        	result.setData(map);
        }
        return result; 
	}
}
