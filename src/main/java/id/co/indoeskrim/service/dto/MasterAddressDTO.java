package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.co.indoeskrim.domain.MasterZipcode;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.service.dto
//	Class Name		: MasterAddressDTO
//	File Name		: MasterAddressDTO.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 5:05:22 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

public class MasterAddressDTO implements Serializable {
	
	private static final long serialVersionUID = 12363254287965321L;
	
	private Long id;
	
	private String province;
	
	private String district;
	
	private String subDistrict;
	
	private String address;
	
	private Boolean isActive;
	
	private Boolean isJabodetabek;
	
	private String lat;
	
	private String lng;
	
	private List<MasterZipcode> masterZipcodes = new ArrayList<MasterZipcode>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsJabodetabek() {
		return isJabodetabek;
	}

	public void setIsJabodetabek(Boolean isJabodetabek) {
		this.isJabodetabek = isJabodetabek;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public List<MasterZipcode> getMasterZipcodes() {
		return masterZipcodes;
	}

	public void setMasterZipcodes(List<MasterZipcode> masterZipcodes) {
		this.masterZipcodes = masterZipcodes;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MasterAddressDTO masterAddressDTO = (MasterAddressDTO) o;
        if (masterAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masterAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasterAddressDTO{" +
            "id=" + getId() +
            ", province='" + getProvince() + "'" +
            ", district='" + getDistrict() + "'" +
            ", subDistrict='" + getSubDistrict() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
	
}
