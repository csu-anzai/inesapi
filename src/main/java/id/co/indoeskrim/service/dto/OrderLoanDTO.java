package id.co.indoeskrim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the OrderLoan entity.
 */
public class OrderLoanDTO implements Serializable {

    private Long id;

    @Size(max = 20)
    private String productNo;

    @Size(max = 3)
    private String orderLoanStatus;

    private Instant returnDate;

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

    public String getOrderLoanStatus() {
        return orderLoanStatus;
    }

    public void setOrderLoanStatus(String orderLoanStatus) {
        this.orderLoanStatus = orderLoanStatus;
    }

    public Instant getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Instant returnDate) {
		this.returnDate = returnDate;
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

        OrderLoanDTO orderLoanDTO = (OrderLoanDTO) o;
        if (orderLoanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderLoanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderLoanDTO{" +
            "id=" + getId() +
            ", productNo='" + getProductNo() + "'" +
            ", orderLoanStatus='" + getOrderLoanStatus() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            ", orderMaster=" + getOrderMasterId() +
            "}";
    }
}
