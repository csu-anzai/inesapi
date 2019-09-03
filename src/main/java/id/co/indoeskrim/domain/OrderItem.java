package id.co.indoeskrim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OrderItem.
 */
@Entity
@Table(name = "order_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "product_no", length = 20)
    private String productNo;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "item_price")
    private Long itemPrice;

    @Column(name = "total_item_price")
    private Long totalItemPrice;

    @Size(max = 3)
    @Column(name = "order_item_status", length = 3)
    private String orderItemStatus;

    @ManyToOne
    @JoinColumn(name = "order_master_id", referencedColumnName = "order_id")
    @JsonIgnoreProperties("orderItems")
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

    public OrderItem productNo(String productNo) {
        this.productNo = productNo;
        return this;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getQty() {
        return qty;
    }

    public OrderItem qty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public OrderItem itemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getTotalItemPrice() {
        return totalItemPrice;
    }

    public OrderItem totalItemPrice(Long totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
        return this;
    }

    public void setTotalItemPrice(Long totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    public OrderItem orderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
        return this;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public OrderItem orderMaster(OrderMaster orderMaster) {
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
        OrderItem orderItem = (OrderItem) o;
        if (orderItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + getId() +
            ", productNo='" + getProductNo() + "'" +
            ", qty=" + getQty() +
            ", itemPrice=" + getItemPrice() +
            ", totalItemPrice=" + getTotalItemPrice() +
            ", orderItemStatus='" + getOrderItemStatus() + "'" +
            "}";
    }
}
