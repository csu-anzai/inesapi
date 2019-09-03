package id.co.indoeskrim.service.impl;

import id.co.indoeskrim.service.OrderMasterService;
import id.co.indoeskrim.domain.OrderItem;
import id.co.indoeskrim.domain.OrderLoan;
import id.co.indoeskrim.domain.OrderMaster;
import id.co.indoeskrim.domain.OrderPayment;
import id.co.indoeskrim.domain.OrderShipping;
import id.co.indoeskrim.repository.OrderMasterRepository;
import id.co.indoeskrim.service.dto.OrderItemDTO;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
import id.co.indoeskrim.service.dto.OrderMasterDTO;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
import id.co.indoeskrim.service.dto.OrderShippingDTO;
import id.co.indoeskrim.service.dto.req.OrderReqDTO;
import id.co.indoeskrim.service.mapper.OrderMasterMapper;
import id.co.indoeskrim.service.mapper.OrderPaymentMapper;
import id.co.indoeskrim.service.mapper.OrderShippingMapper;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing OrderMaster.
 */
@Service
@Transactional
public class OrderMasterServiceImpl implements OrderMasterService {

    private final Logger log = LoggerFactory.getLogger(OrderMasterServiceImpl.class);

    private final OrderMasterRepository orderMasterRepository;

    private final OrderMasterMapper orderMasterMapper;

    private final OrderShippingMapper orderShippingMapper;

    private final OrderPaymentMapper orderPaymentMapper;

    public OrderMasterServiceImpl(OrderMasterRepository orderMasterRepository, OrderMasterMapper orderMasterMapper, 
    		OrderShippingMapper orderShippingMapper, OrderPaymentMapper orderPaymentMapper) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderMasterMapper = orderMasterMapper;
        this.orderShippingMapper = orderShippingMapper;
        this.orderPaymentMapper = orderPaymentMapper;
    }

    /**
     * Save a orderMaster.
     *
     * @param orderMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderMasterDTO save(OrderMasterDTO orderMasterDTO) {
        log.debug("Request to save OrderMaster : {}", orderMasterDTO);

        OrderMaster orderMaster = orderMasterMapper.toEntity(orderMasterDTO);
        orderMaster = orderMasterRepository.save(orderMaster);
        return orderMasterMapper.toDto(orderMaster);
    }

    /**
     * Get all the orderMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderMasters");
        return orderMasterRepository.findAll(pageable)
            .map(orderMasterMapper::toDto);
    }


    /**
     * Get one orderMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderMasterDTO> findOne(String orderId) {
        log.debug("Request to get OrderMaster : {}", orderId);
        return orderMasterRepository.findById(orderId)
            .map(orderMasterMapper::toDto);
    }

    /**
     * Delete the orderMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String orderId) {
        log.debug("Request to delete OrderMaster : {}", orderId);
        orderMasterRepository.deleteById(orderId);
    }

	@Override
	public OrderMasterDTO createOrder(OrderReqDTO orderReqDTO) {
		OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
		
		try {
			OrderMaster orderMaster = orderMasterMapper.mapOrderReqDtoToOrderMaster(orderReqDTO);
			
			orderMaster = orderMasterRepository.save(orderMaster);
			
			orderMasterDTO = orderMasterMapper.toDto(orderMaster);
			//cek create date, last update date
		} catch (Exception e) {
			log.error("ERROR saving order = ", e.getMessage());
			throw new BadRequestAlertException("ERROR saving order = " + e.getMessage(), "orderMaster", "error");
		}
		
		return orderMasterDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public OrderReqDTO getOrder(String orderId) {
		OrderReqDTO orderReqDTO = new OrderReqDTO();
		
		Optional<OrderMaster> opt = orderMasterRepository.findById(orderId);
		if (opt.isPresent()) {
			orderReqDTO = orderMasterMapper.mapOrderMasterToOrderReqDto(opt.get());
		}
		
        return orderReqDTO;
	}

	@Override
	public OrderMasterDTO updateOrderStatusPaid(String orderId) {
		OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
		
		try {
			OrderMaster orderMaster = new OrderMaster();
			
			Optional<OrderMaster> opt = orderMasterRepository.findOrderMasterByOrderIdAndOrderStatus(orderId, "102");
			if (opt.isPresent()) {
				orderMaster = opt.get();
			} else {
				throw new BadRequestAlertException("OrderId not exist", "orderMaster", "error");
			}
			
			orderMaster.setOrderStatus("202");
			orderMaster.getOrderItems().forEach(oi -> {
				oi.setOrderItemStatus("202");
			});
			
			orderMaster = orderMasterRepository.save(orderMaster);
			
			orderMasterDTO = orderMasterMapper.toDto(orderMaster);
		} catch (Exception e) {
			log.error("ERROR updating order = ", e.getMessage());
			throw new BadRequestAlertException("ERROR updating order = " + e.getMessage(), "orderMaster", "error");
		}
		
		return orderMasterDTO;
	}
}
