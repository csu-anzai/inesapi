package id.co.indoeskrim.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.indoeskrim.service.MasterAddressService;
import id.co.indoeskrim.service.dto.MasterAddressCustomDTO;
import id.co.indoeskrim.service.dto.MasterAddressDTO;
import id.co.indoeskrim.web.rest.util.PaginationUtil;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.web.rest
//	Class Name		: AddressResource
//	File Name		: AddressResource.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 5:26:22 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@RestController
@RequestMapping("/api")
public class AddressResource {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ENTITY_NAME = "master_address";
    
    private final MasterAddressService masterAddressService;
    
    public AddressResource(MasterAddressService masterAddressService) {
    	this.masterAddressService = masterAddressService;
    }
    
    @GetMapping("/addr/getProvinces")
    public ResponseEntity<List<String>> getAllProvinces(Pageable pageable) {
    	log.debug("###REST request to get all Provinces");
    	//Page<MasterAddressDTO> page = masterAddressService.getAllProvinces(pageable);
    	List<String> page = masterAddressService.getAllProvinces(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/addr/getProvinces");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
    	return ResponseEntity.ok().body(page);//<List<MasterAddressDTO>>.page;
    }
    
    
    @GetMapping("/addr/getAddressByProvince/{provinceName}")
    public ResponseEntity<List<MasterAddressCustomDTO>> getAddressByProvince(@PathVariable String provinceName, Pageable pageable) {
    	log.debug("###REST request to get a page of Address by Province");
    	Page<MasterAddressCustomDTO> page = masterAddressService.findAllByProvince(pageable, provinceName);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/addr/getAddressByProvince");
    	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    

    

}
