package id.co.indoeskrim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderShipping entity.
 */
public class OrderShippingDTO implements Serializable {

    private Long id;

    private Integer customerAddressId;

    @Size(max = 10)
    private String shippingMethod;

    private Long shippingPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Long getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Long shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderShippingDTO orderShippingDTO = (OrderShippingDTO) o;
        if (orderShippingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderShippingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderShippingDTO{" +
            "id=" + getId() +
            ", customerAddressId=" + getCustomerAddressId() +
            ", shippingMethod='" + getShippingMethod() + "'" +
            ", shippingPrice=" + getShippingPrice() +
            "}";
    }
}
