package id.co.indoeskrim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderShipping.
 */
@Entity
@Table(name = "order_shipping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderShipping extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "customer_address_id")
    private Integer customerAddressId;

    @Size(max = 10)
    @Column(name = "shipping_method", length = 10)
    private String shippingMethod;

    @Column(name = "shipping_price")
    private Long shippingPrice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public OrderShipping customerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
        return this;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public OrderShipping shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Long getShippingPrice() {
        return shippingPrice;
    }

    public OrderShipping shippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
        return this;
    }

    public void setShippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
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
        OrderShipping orderShipping = (OrderShipping) o;
        if (orderShipping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderShipping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderShipping{" +
            "id=" + getId() +
            ", customerAddressId=" + getCustomerAddressId() +
            ", shippingMethod='" + getShippingMethod() + "'" +
            ", shippingPrice=" + getShippingPrice() +
            "}";
    }
}
