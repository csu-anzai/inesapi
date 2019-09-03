package id.co.indoeskrim.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@Size(max = 18)
    @Column(name = "customer_id", length = 18, nullable = false)
	@GenericGenerator(strategy = "id.co.indoeskrim.domain.generator.CustomerIdGenerator", name = "customer_id")
	@GeneratedValue(generator = "customer_id")
    private String customerId;

    @Size(max = 40)
    @Column(name = "name", length = 40)
    private String name;

    @Size(max = 70)
    @Column(name = "email", length = 70)
    private String email;

    @Size(max = 15)
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Size(max = 30)
    @Column(name = "user_id", length = 30)
    private String userId;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerAddress> customerAddresses = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public Customer userId(String userId) {
        this.userId = userId;
        return this;
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

	public Set<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }

    public Customer customerAddresses(Set<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
        return this;
    }

    public Customer addCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddresses.add(customerAddress);
        customerAddress.setCustomer(this);
        return this;
    }

    public Customer removeCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddresses.remove(customerAddress);
        customerAddress.setCustomer(null);
        return this;
    }

    public void setCustomerAddresses(Set<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.getCustomerId() == null || getCustomerId() == null) {
            return false;
        }
        return Objects.equals(getCustomerId(), customer.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCustomerId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "customerId=" + getCustomerId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userId='" + getUserId() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
