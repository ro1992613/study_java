package com.blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.ArticleTypeService;
import com.common.entity.APIResult;
@Controller
@RequestMapping(value="articleType")
public class AticleTypeController {
	@Autowired
	ArticleTypeService articleTypeService;
	
	@ResponseBody
	@RequestMapping(value="getArticleType",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<List<Map<String,Object>>> getArticleType(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<List<Map<String,Object>>> result=new APIResult<>();
		result.setCode(0);
		try {
			List<Map<String,Object>> list=articleTypeService.getArticleType();
			if(list!=null && list.size()>0){
				result.setData(list);
				result.setCode(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
