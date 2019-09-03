package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.OrderItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderItem and its DTO OrderItemDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {

    @Mapping(source = "orderMaster.orderId", target = "orderMasterId")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(source = "orderMasterId", target = "orderMaster")
    OrderItem toEntity(OrderItemDTO orderItemDTO);

    default OrderItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        return orderItem;
    }
}
