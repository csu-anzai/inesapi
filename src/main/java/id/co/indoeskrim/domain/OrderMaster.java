package id.co.indoeskrim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OrderMaster.
 */
@Entity
@Table(name = "order_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderMaster extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 30)
    @Column(name = "order_id", length = 30, nullable = false)
    @GenericGenerator(strategy = "id.co.indoeskrim.domain.generator.OrderIdGenerator", name = "order_id")
    @GeneratedValue(generator = "order_id")
    private String orderId;

    @Size(max = 12)
    @Column(name = "customer_id", length = 12)
    private String customerId;

    @Column(name = "total_price")
    private Long totalPrice;

    @Size(max = 3)
    @Column(name = "order_status", length = 3)
    private String orderStatus;

    @OneToOne(cascade = CascadeType.ALL)    @JoinColumn(unique = true)
    private OrderShipping orderShipping;

    @OneToOne(cascade = CascadeType.ALL)    @JoinColumn(unique = true)
    private OrderPayment orderPayment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderMaster")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderItem> orderItems = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderMaster")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderLoan> orderLoans = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getOrderId() {
        return orderId;
    }

    public OrderMaster orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderMaster customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public OrderMaster totalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderMaster orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public OrderMaster orderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
        return this;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    public OrderPayment getOrderPayment() {
        return orderPayment;
    }

    public OrderMaster orderPayment(OrderPayment orderPayment) {
        this.orderPayment = orderPayment;
        return this;
    }

    public void setOrderPayment(OrderPayment orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderMaster orderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public OrderMaster addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeOrderItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
        orderItem.setOrderMaster(null);
        return this;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<OrderLoan> getOrderLoans() {
        return orderLoans;
    }

    public OrderMaster orderLoans(Set<OrderLoan> orderLoans) {
        this.orderLoans = orderLoans;
        return this;
    }

    public OrderMaster addOrderLoan(OrderLoan orderLoan) {
        this.orderLoans.add(orderLoan);
        orderLoan.setOrderMaster(this);
        return this;
    }

    public OrderMaster removeOrderLoan(OrderLoan orderLoan) {
        this.orderLoans.remove(orderLoan);
        orderLoan.setOrderMaster(null);
        return this;
    }

    public void setOrderLoans(Set<OrderLoan> orderLoans) {
        this.orderLoans = orderLoans;
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
        OrderMaster orderMaster = (OrderMaster) o;
        if (orderMaster.getOrderId() == null || getOrderId() == null) {
            return false;
        }
        return Objects.equals(getOrderId(), orderMaster.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderId());
    }

    @Override
    public String toString() {
        return "OrderMaster{" +
            "orderId=" + getOrderId() +
            ", customerId='" + getCustomerId() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", orderStatus='" + getOrderStatus() + "'" +
            "}";
    }
}
