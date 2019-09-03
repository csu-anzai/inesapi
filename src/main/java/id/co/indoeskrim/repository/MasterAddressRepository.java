package id.co.indoeskrim.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.MasterAddress;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.repository
//	Class Name		: MasterAddressRepository
//	File Name		: MasterAddressRepository.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 4:59:27 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@SuppressWarnings("unused")
@Repository
public interface MasterAddressRepository  extends JpaRepository<MasterAddress, Long> {
	
	@Query(value = "SELECT distinct(province) FROM {h-schema}master_address order by province", nativeQuery = true)
	List<String> getAllProvinces(Pageable pageable);
	
	Page<MasterAddress> findByProvinceAndIsActiveTrue(Pageable pageable, String province);

}
