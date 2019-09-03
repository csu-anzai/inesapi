package id.co.indoeskrim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.MasterAddress;
import id.co.indoeskrim.repository.MasterAddressRepository;
import id.co.indoeskrim.service.MasterAddressService;
import id.co.indoeskrim.service.dto.MasterAddressCustomDTO;
import id.co.indoeskrim.service.mapper.MasterAddressMapper;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.service.impl
//	Class Name		: MasterAddressServiceImpl
//	File Name		: MasterAddressServiceImpl.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 4:58:13 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Service
@Transactional
public class MasterAddressServiceImpl implements MasterAddressService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private final MasterAddressRepository masterAddressRepository;
    
    private final MasterAddressMapper masterAddressMapper;
    
    public MasterAddressServiceImpl(MasterAddressRepository masterAddressRepository, MasterAddressMapper masterAddressMapper) {
    	this.masterAddressRepository = masterAddressRepository;
    	this.masterAddressMapper = masterAddressMapper;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getAllProvinces(Pageable pageable) {
        log.debug("Request to get all Provinces");
        List<String> provinces = masterAddressRepository.getAllProvinces(pageable);
        
        return provinces;
//		return masterAddressRepository.findAllProvince(pageable).map(masterAddressMapper::toDto);
	}
	
	
//	@Override
//	public Page<MasterAddressDTO> findAllByProvince(Pageable pageable, String provinceName) {
//		log.debug("Request to get all Address by Province Name");
//		Page<MasterAddress> page = masterAddressRepository.findAllByProvince(pageable, provinceName);//.map(masterAddressMapper::toDto);
//		return page.map(masterAddressMapper::toDto); //masterAddressRepository.findAllByProvince(pageable, provinceName).map(masterAddressMapper::toDto);
//	}
	

	@Override
	public Page<MasterAddressCustomDTO> findAllByProvince(Pageable pageable, String provinceName) {
		log.debug("Request to get all Address by Province Name");
		Page<MasterAddress> page = masterAddressRepository.findByProvinceAndIsActiveTrue(pageable, provinceName);
		
		Page<MasterAddressCustomDTO> result = null;
		
		List<MasterAddressCustomDTO> arrayList = new ArrayList();
		page.getContent().stream().forEach(obj -> {
			MasterAddressCustomDTO dto = new MasterAddressCustomDTO();
			dto.setAddress(obj.getAddress());
			dto.setProvince(obj.getProvince());
			dto.setDistrict(obj.getDistrict());
			dto.setSubDistrict(obj.getSubDistrict());
			
			List<String> zipList = new ArrayList();
			obj.getMasterZipcodes().forEach(obj2 ->{
				zipList.add(obj2.getZipcode());
				
			});
			dto.setMasterZipcodes(zipList);
			arrayList.add(dto);
			
		});
		
		result = new PageImpl<>(arrayList, page.getPageable(), arrayList.size());
		return result;
		
		//.map(masterAddressMapper::toDto);
		//return page.map(masterAddressMapper::toDto); //masterAddressRepository.findAllByProvince(pageable, provinceName).map(masterAddressMapper::toDto);
	}

}
