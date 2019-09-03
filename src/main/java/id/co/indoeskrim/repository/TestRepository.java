package id.co.indoeskrim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.indoeskrim.domain.Test;

//=================================================================================
//
//	Project			: ines-api
//	Package			: id.co.indoeskrim.repository
//	Class Name		: TestRepository
//	File Name		: TestRepository.java
// 	Author			: yosakre
// 	Date Creation	: Aug 20, 2019, 4:45:17 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public interface TestRepository extends JpaRepository<Test, Long> {

}
