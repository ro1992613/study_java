package com.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String saveFile(MultipartFile file,StringBuffer savePath){
		String fileType = "";
		fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		savePath.append(UUID.randomUUID().toString() + fileType);
		File f = new File(savePath.toString());
		if(!f.getParentFile().exists()){
			f.getParentFile().mkdirs();
		}
		
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return savePath.toString();	
	}
	
	public static String saveFile(MultipartFile file,String savePath){
		File f = new File(savePath);
		if(!f.getParentFile().exists()){
			f.getParentFile().mkdirs();
		}
		
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePath.toString();	
	}

	public static boolean deleteFile(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
			return true;
		}else{
			return file.delete();
		}
			
	}
}
