package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderPayment and its DTO OrderPaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderPaymentMapper extends EntityMapper<OrderPaymentDTO, OrderPayment> {



    default OrderPayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setId(id);
        return orderPayment;
    }
}
