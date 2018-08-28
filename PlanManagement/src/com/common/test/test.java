package com.common.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.MD5Util;
import com.plan.service.ListService;
import com.plan.service.TaskService;

public class test extends BaseJunit{
	@Autowired
	ListService listService;
	
	@Test   //标明是测试方法  
    @Transactional   //标明此方法需使用事务  
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void test1(){
		System.out.println("test1");
		System.out.println(listService.deleteList("1"));
	}
	
	@Test
	public void test2(){
		UsernamePasswordToken token = new UsernamePasswordToken("root", MD5Util.encode("root"));    
        Subject currentUser = SecurityUtils.getSubject();   
        currentUser.login(token);   
        
        System.out.println(currentUser.getPrincipal());;
		//System.out.println(jdbcTemplate.queryForList("select reply_ip from reply limit 1"));
	}
	
}
