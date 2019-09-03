package id.co.indoeskrim.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OrderLoan.
 */
@Entity
@Table(name = "order_loan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderLoan extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "product_no", length = 20)
    private String productNo;

    @Size(max = 3)
    @Column(name = "order_loan_status", length = 3)
    private String orderLoanStatus;

    @Column(name = "return_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Instant returnDate;

    @ManyToOne
    @JoinColumn(name = "order_master_id", referencedColumnName = "order_id")
    @JsonIgnoreProperties("orderLoans")
    private OrderMaster orderMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public OrderLoan productNo(String productNo) {
        this.productNo = productNo;
        return this;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrderLoanStatus() {
        return orderLoanStatus;
    }

    public OrderLoan orderLoanStatus(String orderLoanStatus) {
        this.orderLoanStatus = orderLoanStatus;
        return this;
    }

    public void setOrderLoanStatus(String orderLoanStatus) {
        this.orderLoanStatus = orderLoanStatus;
    }

    public Instant getReturnDate() {
		return returnDate;
	}

    public OrderLoan returnDate(Instant returnDate) {
        this.returnDate = returnDate;
        return this;
    }

	public void setReturnDate(Instant returnDate) {
		this.returnDate = returnDate;
	}

	public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public OrderLoan orderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
        return this;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
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
        OrderLoan orderLoan = (OrderLoan) o;
        if (orderLoan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderLoan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderLoan{" +
            "id=" + getId() +
            ", productNo='" + getProductNo() + "'" +
            ", orderLoanStatus='" + getOrderLoanStatus() + "'" +
            ", returnDate='" + getReturnDate() + "'" +
            "}";
    }
}
