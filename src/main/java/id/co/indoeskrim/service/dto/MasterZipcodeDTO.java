package id.co.indoeskrim.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.service.dto
//	Class Name		: MasterZipcodeDTO
//	File Name		: MasterZipcodeDTO.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 5:10:38 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public class MasterZipcodeDTO implements Serializable {

	private static final long serialVersionUID = 12363254287965321L;
	
	private Long id;
	
	private String zipcode;

	private Long addressId;
	
	@JsonIgnore
	private MasterAddressDTO masterAddressDTO;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public MasterAddressDTO getMasterAddressDTO() {
		return masterAddressDTO;
	}

	public void setMasterAddressDTO(MasterAddressDTO masterAddressDTO) {
		this.masterAddressDTO = masterAddressDTO;
	}
	
	
}
