package id.co.indoeskrim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//=================================================================================
//
//	Project			: ines-bo
//	Package			: id.co.indoeskrim.domain
//	Class Name		: MasterZipcode
//	File Name		: MasterZipcode.java
// 	Author			: yosakre
// 	Date Creation	: Aug 15, 2019, 3:03:29 PM
//	
//  Modification History:
//  No			Name			Date		Remarks
//  1. 
//=================================================================================

@Entity
@Table(name = "master_zipcode")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MasterZipcode extends AbstractAuditingEntity {
    
    @Id
    @SequenceGenerator(name = "ms_zipcd_pk_seq", sequenceName = "ms_zipcd_pk_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_zipcd_pk_seq")
    private Long id;

    @Size(max = 7)
    @Column(name = "zipcode", length = 7)
    private String zipcode;
    
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonIgnoreProperties("masterZipcodes")
    private MasterAddress masterAddress;
    
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

	public MasterAddress getMasterAddress() {
		return masterAddress;
	}

	public void setMasterAddress(MasterAddress masterAddress) {
		this.masterAddress = masterAddress;
	}
	
	@Override
    public String toString() {
    	return "MasterAddress{" +
                "id=" + getId() + "'" +
                ", zipcode=" + getZipcode() + "'" +
                "}";
    }
    

}
