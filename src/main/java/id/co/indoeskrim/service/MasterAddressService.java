package id.co.indoeskrim.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.service.dto.MasterAddressCustomDTO;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.service
//	Class Name		: MasterAddressService
//	File Name		: MasterAddressService.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 4:58:07 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public interface MasterAddressService {

    //Page<MasterAddressDTO> getAllProvinces(Pageable pageable);
	List<String> getAllProvinces(Pageable pageable);
	
	//Page<MasterAddressDTO> findAllByProvince(Pageable pageable, String provinceName);
	Page<MasterAddressCustomDTO> findAllByProvince(Pageable pageable, String provinceName);

}
