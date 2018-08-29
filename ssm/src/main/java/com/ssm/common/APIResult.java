package com.ssm.common;

/**
 * @description 灏佽API鎺ュ彛鏁版嵁
 * @version V1.0
 */
public class APIResult<T> {
	private int code;//鐘舵�佺爜
	private String msg;//娑堟伅,1-鎴愬姛
	private T data;//鏁版嵁
	private String token;//瀹夊叏浠ょ墝
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
