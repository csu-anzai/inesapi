package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.OrderShippingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderShipping and its DTO OrderShippingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderShippingMapper extends EntityMapper<OrderShippingDTO, OrderShipping> {



    default OrderShipping fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderShipping orderShipping = new OrderShipping();
        orderShipping.setId(id);
        return orderShipping;
    }
}
