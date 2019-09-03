package id.co.indoeskrim.service.mapper;

import id.co.indoeskrim.domain.*;
import id.co.indoeskrim.service.dto.OrderItemDTO;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
import id.co.indoeskrim.service.dto.OrderMasterDTO;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
import id.co.indoeskrim.service.dto.OrderShippingDTO;
import id.co.indoeskrim.service.dto.req.OrderReqDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

/**
 * Mapper for the entity OrderMaster and its DTO OrderMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderShippingMapper.class, OrderPaymentMapper.class})
public interface OrderMasterMapper extends EntityMapper<OrderMasterDTO, OrderMaster> {

    @Mapping(source = "orderShipping.id", target = "orderShippingId")
    @Mapping(source = "orderPayment.id", target = "orderPaymentId")
    OrderMasterDTO toDto(OrderMaster orderMaster);

    @Mapping(source = "orderShippingId", target = "orderShipping")
    @Mapping(source = "orderPaymentId", target = "orderPayment")
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "orderLoans", ignore = true)
    OrderMaster toEntity(OrderMasterDTO orderMasterDTO);

    default OrderMaster fromId(String orderId) {
        if (orderId == null) {
            return null;
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        return orderMaster;
    }

	default OrderMaster mapOrderReqDtoToOrderMaster(OrderReqDTO orderReqDTO) {
		OrderMaster om = new OrderMaster();
		BeanUtils.copyProperties(orderReqDTO, om);
		
		orderReqDTO.getOrderItemDTOList().forEach(dto -> {
			OrderItem oi = new OrderItem();
			BeanUtils.copyProperties(dto, oi);
			om.addOrderItem(oi);
		});
		
		orderReqDTO.getOrderLoanDTOList().forEach(dto -> {
			OrderLoan ol = new OrderLoan();
			BeanUtils.copyProperties(dto, ol);
			om.addOrderLoan(ol);
		});
		
		OrderShipping os = new OrderShipping();
		BeanUtils.copyProperties(orderReqDTO.getOrderShippingDTO(), os);
		om.setOrderShipping(os);
		
		OrderPayment op = new OrderPayment();
		BeanUtils.copyProperties(orderReqDTO.getOrderPaymentDTO(), op);
		om.setOrderPayment(op);
		
		return om;
	}

	default OrderReqDTO mapOrderMasterToOrderReqDto(OrderMaster orderMaster) {
		OrderReqDTO or = new OrderReqDTO();
		BeanUtils.copyProperties(orderMaster, or);
		
		List<OrderItemDTO> oidl = new ArrayList<OrderItemDTO>();
		orderMaster.getOrderItems().forEach(oi -> {
			OrderItemDTO oid = new OrderItemDTO();
			BeanUtils.copyProperties(oi, oid);
			oidl.add(oid);
		});
		or.setOrderItemDTOList(oidl);
		
		List<OrderLoanDTO> oldl = new ArrayList<OrderLoanDTO>();
		orderMaster.getOrderLoans().forEach(ol -> {
			OrderLoanDTO old = new OrderLoanDTO();
			BeanUtils.copyProperties(ol, old);
			oldl.add(old);
		});
		or.setOrderLoanDTOList(oldl);
		
		OrderShippingDTO osd = new OrderShippingDTO();
		BeanUtils.copyProperties(orderMaster.getOrderShipping(), osd);
		or.setOrderShippingDTO(osd);
		
		OrderPaymentDTO opd = new OrderPaymentDTO();
		BeanUtils.copyProperties(orderMaster.getOrderPayment(), opd);
		or.setOrderPaymentDTO(opd);
		
		return or;
	}
}
