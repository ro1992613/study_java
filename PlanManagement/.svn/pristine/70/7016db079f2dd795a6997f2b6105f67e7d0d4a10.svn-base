package com.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.MD5Util;

@Controller
@RequestMapping("/test")
public class test {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/login",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("user", request.getParameter("user"));
		System.out.println();
		System.out.println("###########"+session.getId());
		System.out.println("*********login"+request.getParameter("user"));
		return "login"+request.getParameter("user");
	}
	@RequiresRoles("admin") 
	@RequestMapping(value="/logout",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		System.out.println("logout"+subject.isAuthenticated());
		subject.logout();
	}
	

	@RequestMapping(value="/t1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
	@ResponseBody
	public void getuser(HttpServletRequest request, HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipal());
	}
	
	@RequestMapping(value="/t2",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.GET)
	@ResponseBody
	public void t2(HttpServletRequest request, HttpServletResponse response){
		UsernamePasswordToken token = new UsernamePasswordToken("root", MD5Util.encode("root"));    
        Subject currentUser = SecurityUtils.getSubject();   
        currentUser.login(token);    
	}
	
	@RequestMapping(value="/show",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String show(HttpServletRequest request, Model model){
		 String msg = "";
	        String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
System.out.println("********");
	        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
	        token.setRememberMe(true);
	        Subject subject = SecurityUtils.getSubject();
	        try {
	            subject.login(token);
	            if (subject.isAuthenticated()) {
	                return "success";
	            } else {
	                return "failed";
	            }
	        } catch (IncorrectCredentialsException e) {
	            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (ExcessiveAttemptsException e) {
	            msg = "登录失败次数过多";
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (LockedAccountException e) {
	            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (DisabledAccountException e) {
	            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (ExpiredCredentialsException e) {
	            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (UnknownAccountException e) {
	            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        } catch (UnauthorizedException e) {
	            msg = "您没有得到相应的授权！" + e.getMessage();
	            model.addAttribute("message", msg);
	            System.out.println(msg);
	        }
	        return msg;
	}
}
