package com.blog.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.blog.service.ArticleService;
import com.common.entity.APIResult;
import com.common.utils.DateUtils;
import com.common.utils.Page;
import com.common.utils.StringUtil;

@Controller
@RequestMapping(value="article")
public class ArticleController {

	@Resource
	ArticleService articleService;
	
	static int PAGE_NUM = 20;
	
	@ResponseBody
	@RequestMapping(value = "insertArticle",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<String> insertArticle(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		Map<String,Object> article=new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String article_author_id=user.get("user_id").toString();
		String article_author_name=user.get("user_alias").toString();
		
		String article_title=request.getParameter("article_title");
		String article_content=request.getParameter("article_content");
		String article_summary=request.getParameter("article_summary");
		String article_type=request.getParameter("article_type");		
		Date article_createtime=new Date();
		String article_id="article_"+StringUtil.getID();
		
		article.put("article_author_id",article_author_id);
		article.put("article_author_name",article_author_name);
		article.put("article_clicktimes",0);
		article.put("article_content",article_content);
		article.put("article_createtime",article_createtime);
		article.put("article_id",article_id);
		article.put("article_logo_url","");
		article.put("article_replytimes",0);
		article.put("article_summary",article_summary);
		article.put("article_title",article_title);
		article.put("article_type",article_type);
				
		try {			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = multipartRequest.getFiles("article_logo");
			if(files!=null && files.size()>0){
				MultipartFile file=files.get(0);
				String conarticlePath = request.getServletContext().getRealPath(""); 
				String filePath="savePic/"+DateUtils.getFormatTime(new Date(), "yyyy-MM-dd")+"/"
						+article_id+ file.getOriginalFilename();
				File outFile = new File(conarticlePath+"/"+filePath );
				if (!outFile.exists()) {
					outFile.getParentFile().mkdirs();
					outFile.createNewFile();
				} 
				file.transferTo(outFile);		
				article.put("article_logo_url",filePath);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(articleService.insertArticle(article)){
			result.setCode(1);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateArticle",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<String> updateArticle(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		
		String article_title=request.getParameter("article_title");
		String article_content=request.getParameter("article_content");
		String article_summary=request.getParameter("article_summary");
		String article_type=request.getParameter("article_type");
		String article_id=request.getParameter("article_id");
		Map<String,Object> article=articleService.getArticle(article_id);

		if(!StringUtil.IsNullOrEmpty(article_content)){article.put("article_content",article_content);}
		if(!StringUtil.IsNullOrEmpty(article_summary)){article.put("article_summary",article_summary);}
		if(!StringUtil.IsNullOrEmpty(article_title)){article.put("article_title",article_title);}
		if(!StringUtil.IsNullOrEmpty(article_type)){article.put("article_type",article_type);}
				
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = multipartRequest.getFiles("article_logo");
			if(files!=null && files.size()>0){
				MultipartFile file=files.get(0);
				String conarticlePath = request.getServletContext().getRealPath(""); 
				String filePath="savePic/"+DateUtils.getFormatTime(new Date(), "yyyy-MM-dd")+"/"
						+article_id+ file.getOriginalFilename();
				File outFile = new File(conarticlePath+"/"+filePath );
				if (!outFile.exists()) {
					outFile.getParentFile().mkdirs();
					outFile.createNewFile();
				} 
				file.transferTo(outFile);		
				article.put("article_logo_url",filePath);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(articleService.updateArticle(article)){
			result.setCode(1);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getArticleList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<Page<Map<String,Object>>> getArticleList(HttpServletRequest request, HttpServletResponse response){
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		String page=request.getParameter("page");
		String article_type=request.getParameter("article_type");
		String article_author_id=request.getParameter("user_id");
		String key=request.getParameter("key");		
		try {
			result.setData(new Page<Map<String, Object>>(articleService.getArticleCount(article_type,article_author_id,key)
					,Integer.parseInt(page)
					,PAGE_NUM
					,articleService.getArticleList(article_type,article_author_id,key,Integer.parseInt(page), PAGE_NUM)));
			result.setCode(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getHottestArticleList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<List<Map<String,Object>>> getHottestArticleList(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		try {
			List<Map<String,Object>> list=articleService.getHottestArticleList();
			if(list!=null && list.size()>0 ){
				result.setData(list);
				result.setCode(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getMyArticleList",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<Page<Map<String,Object>>> getMyArticleList(HttpServletRequest request, HttpServletResponse response){
		APIResult<Page<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) subject.getPrincipal();
		String article_author_id=user.get("user_id").toString();
		
		String page=request.getParameter("page");
		String article_type=request.getParameter("article_type");
		String key=request.getParameter("key");
		try {
			result.setData(new Page<Map<String, Object>>(articleService.getArticleCount(article_type,article_author_id,key)
					,Integer.parseInt(page)
					,PAGE_NUM
					,articleService.getArticleList(article_type,article_author_id,key,Integer.parseInt(page), PAGE_NUM)));
			result.setCode(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "getArticle",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<Map<String,Object>> getArticle(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "article_id") String article_id){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		Map<String,Object> article = articleService.getArticle(article_id);
		if(article!=null){
			result.setCode(1);
			result.setData(article);
			article.put("article_clicktimes",(Integer)article.get("article_clicktimes")+1);
			articleService.updateArticle(article);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteArticle",produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public APIResult<String> deleteArticle(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "article_id") String article_id){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		boolean rs = articleService.deleteArticle(article_id);
		if(rs){
			result.setCode(1);
		}
		return result;
	}
}
