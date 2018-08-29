package com.silence.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silence.sample.dao.SampleDAO;

@Service
public class SampleService {

	@Autowired
	SampleDAO sampleDAO;

	public List<Map<String, Object>> getSamples() {
		// TODO Auto-generated method stub
		return sampleDAO.getSamples();
	}
}
