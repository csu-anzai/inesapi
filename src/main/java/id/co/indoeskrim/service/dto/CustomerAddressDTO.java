package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import id.co.indoeskrim.domain.Customer;
import id.co.indoeskrim.domain.MasterAddress;

/**
 * A DTO for the CustomerAddress entity.
 */
public class CustomerAddressDTO implements Serializable {

	private Long id;

	@Size(max = 150)
	private String address;

	@Size(max = 40)
	private String receiverName;

	@Size(max = 15)
	private String receiverPhone;

	@Size(max = 9)
	private String postalCode;

	private String lat;

	private String lng;
	
	private Long addressId;

	@JsonIgnore
	private Customer customer;
	
	@JsonIgnore
	public MasterAddress masterAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

//	public String getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public MasterAddress getMasterAddress() {
		return masterAddress;
	}

	public void setMasterAddress(MasterAddress masterAddress) {
		this.masterAddress = masterAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CustomerAddressDTO customerAddressDTO = (CustomerAddressDTO) o;
		if (customerAddressDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), customerAddressDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "CustomerAddressDTO{" 
				+ "id=" + getId() 
				+ ", address='" + getAddress() + "'" 
				+ ", receiverName='" + getReceiverName() + "'" 
				+ ", receiverPhone='" + getReceiverPhone() + "'" 
				+ ", postalCode='" + getPostalCode() + "'" 
				+ ", lat='" + getLat() + "'" 
				+ ", lng='" + getLng() + "'" 
				+ ", customer=" + getCustomer() + "'" 
				+ ", masterAddress='" + getMasterAddress() + "'" 
				+ "}";
	}
}
