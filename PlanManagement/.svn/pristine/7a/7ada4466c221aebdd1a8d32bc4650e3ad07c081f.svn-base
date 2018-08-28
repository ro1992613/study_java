package com.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.service.ImgService;
import com.common.entity.APIResult;

@Controller
@RequestMapping(value="pic")
public class ImgController {
	
	@Autowired
	ImgService imgService;
	
	@ResponseBody
	@RequestMapping(value="getJandanImg",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public APIResult<List<String>> getImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		APIResult<List<String>> result=new APIResult<>();
		result.setCode(0);
		String page=request.getParameter("page");
		int index=0;
		try {
			index=Integer.parseInt(page);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			List<String> list=imgService.getImgList(index);
			result.setData(list);
			result.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
