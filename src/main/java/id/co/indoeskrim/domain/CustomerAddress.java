package id.co.indoeskrim.domain;


import java.io.Serializable;
import java.util.Objects;

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

/**
 * A CustomerAddress.
 */
@Entity
@Table(name = "customer_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomerAddress extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "customer_addr_pk_seq", sequenceName = "customer_addr_pk_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_addr_pk_seq")
    private Long id;

    @Size(max = 150)
    @Column(name = "address", length = 150)
    private String address;

    @Size(max = 40)
    @Column(name = "receiver_name", length = 40)
    private String receiverName;

    @Size(max = 15)
    @Column(name = "receiver_phone", length = 15)
    private String receiverPhone;

    @Size(max = 9)
    @Column(name = "postal_code", length = 9)
    private String postalCode;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties("masterAddresses")
    private MasterAddress masterAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("customerAddresses")
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public CustomerAddress address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public CustomerAddress receiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public CustomerAddress receiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
        return this;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public CustomerAddress postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLat() {
        return lat;
    }

    public CustomerAddress lat(String lat) {
        this.lat = lat;
        return this;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public CustomerAddress lng(String lng) {
        this.lng = lng;
        return this;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAddress customer(Customer customer) {
        this.customer = customer;
        return this;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerAddress customerAddress = (CustomerAddress) o;
        if (customerAddress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerAddress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerAddress{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", receiverName='" + getReceiverName() + "'" +
            ", receiverPhone='" + getReceiverPhone() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", lat='" + getLat() + "'" +
            ", lng='" + getLng() + "'" +
            "}";
    }
}
