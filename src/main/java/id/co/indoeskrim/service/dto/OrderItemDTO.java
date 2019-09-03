package id.co.indoeskrim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OrderItem entity.
 */
public class OrderItemDTO implements Serializable {

    private Long id;

    @Size(max = 20)
    private String productNo;

    private Integer qty;

    private Long itemPrice;

    private Long totalItemPrice;

    @Size(max = 3)
    private String orderItemStatus;

    private String orderMasterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Long getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Long getTotalItemPrice() {
		return totalItemPrice;
	}

	public void setTotalItemPrice(Long totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}

    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public String getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(String orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderItemDTO orderItemDTO = (OrderItemDTO) o;
        if (orderItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
            "id=" + getId() +
            ", productNo='" + getProductNo() + "'" +
            ", qty=" + getQty() +
            ", itemPrice=" + getItemPrice() +
            ", totalItemPrice=" + getTotalItemPrice() +
            ", orderItemStatus='" + getOrderItemStatus() + "'" +
            ", orderMaster=" + getOrderMasterId() +
            "}";
    }
}
