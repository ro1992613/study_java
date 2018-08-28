package com.blog.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.ReplyService;
import com.common.entity.APIResult;
import com.common.utils.OtherUtils;
import com.common.utils.Page;
import com.common.utils.StringUtil;
import com.blog.service.ArticleService;

@Controller 
@RequestMapping(value="reply")
public class ReplyController {
	
	static int PAGE_NUM = 10;
	@Resource
	ReplyService replyService;
	@Resource
	ArticleService articleService;
	@ResponseBody
	@RequestMapping(value="insertReply",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<String> insertReply(HttpServletRequest request,HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		Map<String, Object> reply=new HashMap<String, Object>();
		String article_id=request.getParameter("article_id");
		String article_title=request.getParameter("article_title");
		String user_id=request.getParameter("user_id");
		String reply_name=request.getParameter("reply_name");
		String user_alias=request.getParameter("user_alias");
		String reply_content=request.getParameter("reply_content");
		
		reply.put("reply_text_id",article_id);
		reply.put("reply_text_title", article_title);
		reply.put("reply_user_id",user_id);
		reply.put("reply_name",reply_name);
		reply.put("reply_user_alias", user_alias);
		reply.put("reply_id","reply"+StringUtil.getID());
		reply.put("reply_time", new Timestamp((new Date()).getTime()));
		reply.put("reply_content",reply_content);
		reply.put("reply_ip",OtherUtils.getIpAddress(request));		
		
		try {
			boolean rs=replyService.insertReply(reply);
			if(rs){
				articleService.updateArticleReplytimes(article_id);
				result.setCode(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
				
	}
	@ResponseBody
	@RequestMapping(value="getReplyList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<Page<Map<String,Object>>> getReplyList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		String article_id=request.getParameter("article_id"); 
		String page=request.getParameter("page");
		
		try {
			Page<Map<String, Object>> replys=new Page<Map<String, Object>>(replyService.getReplyCount(article_id)
					,Integer.parseInt(page)
					,PAGE_NUM
					,replyService.getReplyList(article_id,Integer.parseInt(page), PAGE_NUM));
			result.setData(replys);
			result.setCode(1);
		} catch (Exception e) {
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="getLatestReplyList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<List<Map<String,Object>>> getLatestReplyList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<List<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		try {
			List<Map<String,Object>> list=replyService.getLatestReplyList();
			if(list!=null && list.size()>0){
				result.setData(list);
				result.setCode(1);
			}
		} catch (Exception e) {
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteReply",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<String> deleteReply(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String reply_id=request.getParameter("reply_id");
		
		try {
			boolean rs=replyService.deleteReply(reply_id);
			if(rs){
				result.setCode(1);
			}
		} catch (Exception e) {
		}

		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="deleteReplyList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<String> deleteReplyList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String article_id=request.getParameter("article_id"); 
		try {
			boolean rs=replyService.deleteReplyList(article_id);
			if(rs){
				result.setCode(1);
			}
		} catch (Exception e) {
		}

		return result;
	}
}
