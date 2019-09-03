package id.co.indoeskrim.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.Test;
import id.co.indoeskrim.service.dto.TestDTO;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.service.mapper
//	Class Name		: TestMapper
//	File Name		: TestMapper.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:55:39 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Mapper(componentModel = "spring", uses = {})
public interface TestMapper extends EntityMapper<TestDTO, Test> {
	
	Test toEntity(TestDTO obj);
	
	TestDTO toDto(Test test);

	default Test fromId(Long id) {
		if(id == null) {
			return null;
		}
		Test test = new Test();
		test.setId(id);
		return test;
	}
}
