package id.co.indoeskrim.service.dto;
import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;

/**
 * A DTO for the Customer entity.
 */
public class CustomerDTO implements Serializable {

    private String customerId;

    @Size(max = 40)
    private String name;

    @Size(max = 70)
    private String email;

    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 30)
    private String userId;
    
    private Boolean isActive;
    
    private Long addressId;
    
    private CustomerAddressDTO customerAddress = new CustomerAddressDTO();
    
    private UserDTO user = new UserDTO();


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public CustomerAddressDTO getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddressDTO customerAddress) {
		this.customerAddress = customerAddress;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getCustomerId() == null || getCustomerId() == null) {
            return false;
        }
        return Objects.equals(getCustomerId(), customerDTO.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCustomerId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "customerId=" + getCustomerId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userId='" + getUserId() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", userObj='" + getUser() + "'" +
            "}";
    }
}
