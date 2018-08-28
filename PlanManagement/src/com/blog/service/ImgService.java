package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.blog.dao.ImgDAO;
@Component
public class ImgService {

	@Autowired
	ImgDAO imgDAO;
	
	
	public List<String> getImgList(int page){
		return imgDAO.getImgList(imgDAO.getMaxPage()-page);
	}
}
