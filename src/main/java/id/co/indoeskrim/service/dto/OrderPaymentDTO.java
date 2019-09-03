package id.co.indoeskrim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderPayment entity.
 */
public class OrderPaymentDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String paymentMethod;

    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderPaymentDTO orderPaymentDTO = (OrderPaymentDTO) o;
        if (orderPaymentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderPaymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderPaymentDTO{" +
            "id=" + getId() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
