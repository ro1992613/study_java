package com.common.utils;

import java.security.MessageDigest;

/**
 * 
 * @title MD5Util.java
 * @author SongDaYe
 * @time 2016年3月15日 上午9:31:58
 * @description MD5工具类
 * @version V1.0
 */
public class MD5Util {
	/**
	 * 
	 * @author SongDaYe
	 * @time 2016年3月15日 上午9:32:08
	 * @description 单次MD5加密
	 * @param source String 需要加密的内容
	 * @return
	 */
	public static String encode(String source) {
		return MD5(source);
	}
	
	/**
	 * 
	 * @author SongDaYe
	 * @time 2016年3月15日 上午9:33:00
	 * @description 双重MD5加密
	 * @param source String 需要加密的内容
	 * @return
	 */
	public static String doubleEncode(String source){
		return MD5(MD5(source));
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}