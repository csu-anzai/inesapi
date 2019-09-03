package id.co.indoeskrim.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.domain
//	Class Name		: MasterAddress
//	File Name		: MasterAddress.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 3:03:20 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

/**
 * A MasterAddress.
 */
@Entity
@Table(name = "master_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MasterAddress extends AbstractAuditingEntity {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "ms_addr_pk_seq", sequenceName = "ms_addr_pk_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_addr_pk_seq")
    private Long id;

    @Size(max = 50)
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 50)
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50)
    @Column(name = "sub_district", length = 50)
    private String subDistrict;

    @Size(max = 150)
    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_jabodetabek")
    private Boolean isJabodetabek;

    @Size(max = 150)
    @Column(name = "lat", length = 150)
    private String lat;

    @Size(max = 150)
    @Column(name = "lng", length = 150)
    private String lng;

    @OneToMany(mappedBy = "masterAddress")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MasterZipcode> masterZipcodes = new HashSet<>();

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


	public Set<MasterZipcode> getMasterZipcodes() {
        return masterZipcodes;
    }

    public MasterAddress masterZipcodes(Set<MasterZipcode> masterZipcodes) {
        this.masterZipcodes = masterZipcodes;
        return this;
    }

    public MasterAddress addMasterZipcode(MasterZipcode masterZipcode) {
        this.masterZipcodes.add(masterZipcode);
        masterZipcode.setMasterAddress(this);
        return this;
    }
	
	
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MasterAddress masterAddress = (MasterAddress) o;
        if (masterAddress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masterAddress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasterAddress{" +
            "id=" + getId() +
            ", province='" + getProvince() + "'" +
            ", district='" + getDistrict() + "'" +
            ", subDistrict='" + getSubDistrict() + "'" +
            ", address='" + getAddress() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", isJabodetabek='" + getIsJabodetabek() + "'" +
            ", lat='" + getLat() + "'" +
            ", lng='" + getLng() + "'" +
            "}";
    }    
    

}
