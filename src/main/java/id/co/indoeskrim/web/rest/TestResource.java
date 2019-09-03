package id.co.indoeskrim.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.indoeskrim.domain.Test;
import id.co.indoeskrim.service.TestService;
import id.co.indoeskrim.service.dto.TestDTO;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.web.rest
//	Class Name		: TestResource
//	File Name		: TestResource.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:49:13 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@RestController
@RequestMapping("/test")
public class TestResource {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ENTITY_NAME = "test";
    
    private final TestService testService;
    
    public TestResource(TestService testService) {
    	this.testService = testService;
    }

    
    @PostMapping("/test")
    public ResponseEntity<TestDTO> save(@Valid @RequestBody TestDTO testDTO) throws URISyntaxException {
        log.debug("REST request to save Test Data : {}", testDTO);
        if (testDTO.getId() != null) {
            throw new BadRequestAlertException("A new test data cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        TestDTO result = testService.save(testDTO);
        return ResponseEntity.created(new URI("/api/test/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    	
    }
    
}
