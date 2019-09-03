package id.co.indoeskrim.service.dto.req;

import javax.validation.constraints.*;

import id.co.indoeskrim.service.dto.OrderItemDTO;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
import id.co.indoeskrim.service.dto.OrderShippingDTO;

import java.io.Serializable;
import java.util.List;

public class OrderReqDTO implements Serializable {

    @Size(max = 30)
    private String orderId;

	@Size(max = 12)
    private String customerId;

    private Long totalPrice;

    @Size(max = 3)
    private String orderStatus;

    private List<OrderItemDTO> orderItemDTOList;

    private List<OrderLoanDTO> orderLoanDTOList;

    private OrderShippingDTO orderShippingDTO;

    private OrderPaymentDTO orderPaymentDTO;

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

    public List<OrderItemDTO> getOrderItemDTOList() {
		return orderItemDTOList;
	}

	public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
		this.orderItemDTOList = orderItemDTOList;
	}

	public List<OrderLoanDTO> getOrderLoanDTOList() {
		return orderLoanDTOList;
	}

	public void setOrderLoanDTOList(List<OrderLoanDTO> orderLoanDTOList) {
		this.orderLoanDTOList = orderLoanDTOList;
	}

	public OrderShippingDTO getOrderShippingDTO() {
		return orderShippingDTO;
	}

	public void setOrderShippingDTO(OrderShippingDTO orderShippingDTO) {
		this.orderShippingDTO = orderShippingDTO;
	}

	public OrderPaymentDTO getOrderPaymentDTO() {
		return orderPaymentDTO;
	}

	public void setOrderPaymentDTO(OrderPaymentDTO orderPaymentDTO) {
		this.orderPaymentDTO = orderPaymentDTO;
	}

    @Override
    public String toString() {
        return "OrderReqDTO{" +
            "orderId='" + getOrderId() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", orderStatus='" + getOrderStatus() + "'" +
            "}";
    }
}
