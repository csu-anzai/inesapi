package id.co.indoeskrim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderMaster entity.
 */
public class OrderMasterDTO implements Serializable {

    @Size(max = 30)
    private String orderId;

    @Size(max = 12)
    private String customerId;

    private Long totalPrice;

    @Size(max = 3)
    private String orderStatus;

    private Long orderShippingId;

    private Long orderPaymentId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderShippingId() {
        return orderShippingId;
    }

    public void setOrderShippingId(Long orderShippingId) {
        this.orderShippingId = orderShippingId;
    }

    public Long getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(Long orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderMasterDTO orderMasterDTO = (OrderMasterDTO) o;
        if (orderMasterDTO.getOrderId() == null || getOrderId() == null) {
            return false;
        }
        return Objects.equals(getOrderId(), orderMasterDTO.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderId());
    }

    @Override
    public String toString() {
        return "OrderMasterDTO{" +
            "orderId=" + getOrderId() +
            ", customerId='" + getCustomerId() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", orderShipping=" + getOrderShippingId() +
            ", orderPayment=" + getOrderPaymentId() +
            "}";
    }
}
