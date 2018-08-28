package com.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TokenUtil {
	public static String getToken(Map<String,String> systemMap){
		Set<String> keys = systemMap.keySet();
		StringBuffer buffer = new StringBuffer();
		for(String key :keys){
			String value = systemMap.get(key);
			buffer.append( AESUtil.encrypt(value.toString(),key));
		}
		
		String token = MD5Util.doubleEncode(buffer.toString());
		return token;
	}
	
	public static void main(String[] args) {
		Map<String, String> systemMap = new HashMap<>();
		systemMap.put("system_id", "1025");
		systemMap.put("system_key", "silence_sichuan_gongan");
		
		System.out.println(getToken(systemMap));
	}
}
