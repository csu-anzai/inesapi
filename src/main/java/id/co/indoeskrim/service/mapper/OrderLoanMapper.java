package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.OrderLoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderLoan and its DTO OrderLoanDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
public interface OrderLoanMapper extends EntityMapper<OrderLoanDTO, OrderLoan> {

    @Mapping(source = "orderMaster.orderId", target = "orderMasterId")
    OrderLoanDTO toDto(OrderLoan orderLoan);

    @Mapping(source = "orderMasterId", target = "orderMaster")
    OrderLoan toEntity(OrderLoanDTO orderLoanDTO);

    default OrderLoan fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderLoan orderLoan = new OrderLoan();
        orderLoan.setId(id);
        return orderLoan;
    }
}
