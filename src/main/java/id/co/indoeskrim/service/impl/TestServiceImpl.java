package id.co.indoeskrim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Test;
import id.co.indoeskrim.repository.TestRepository;
import id.co.indoeskrim.service.TestService;
import id.co.indoeskrim.service.dto.TestDTO;
import id.co.indoeskrim.service.mapper.TestMapper;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.service.impl
//	Class Name		: TestServiceImpl
//	File Name		: TestServiceImpl.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:44:19 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Service
@Transactional
public class TestServiceImpl implements TestService{

    private final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    
    private final TestRepository repository;
    
    private final TestMapper mapper;
    
    public TestServiceImpl(TestRepository repository, TestMapper mapper) {
    	this.repository = repository;
    	this.mapper = mapper;
    }
    
    @Override
    public TestDTO save(TestDTO testDTO) {
    	log.debug("Request to save Test data");
    	Test obj = mapper.toEntity(testDTO);
    	repository.save(obj);
    	
    	// Return the object
    	testDTO = new TestDTO();
    	testDTO = mapper.toDto(obj);
    	return testDTO;
    }

}
