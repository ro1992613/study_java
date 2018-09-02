package com.ssm.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.sample.dao.SampleDAO;

@Service
//@Transactional
public class SampleService {

	@Autowired
	SampleDAO sampleDAO;

	public List<Map<String, Object>> getSamples() {
		// TODO Auto-generated method stub
		return sampleDAO.getSamples();
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void testTransaction() {
		sampleDAO.insertTest();
		sampleDAO.findTest();
		sampleDAO.insertTest1();
		sampleDAO.findTest();
	}
}
