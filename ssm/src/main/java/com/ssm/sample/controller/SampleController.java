package com.ssm.sample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.APIResult;
import com.ssm.sample.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="/getSamples",method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<Map<String,Object>>> getSamples() {
		APIResult<List<Map<String,Object>>> result=new APIResult<List<Map<String, Object>>>();
		result.setCode(-1);
		try {
			List<Map<String,Object>> list=sampleService.getSamples();
			result.setData(list);
			result.setCode(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
