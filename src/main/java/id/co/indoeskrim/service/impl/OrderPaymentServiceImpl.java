package id.co.indoeskrim.service.impl;

import id.co.indoeskrim.service.OrderPaymentService;
import id.co.indoeskrim.domain.OrderPayment;
import id.co.indoeskrim.repository.OrderPaymentRepository;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
import id.co.indoeskrim.service.mapper.OrderPaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OrderPayment.
 */
@Service
@Transactional
public class OrderPaymentServiceImpl implements OrderPaymentService {

    private final Logger log = LoggerFactory.getLogger(OrderPaymentServiceImpl.class);

    private final OrderPaymentRepository orderPaymentRepository;

    private final OrderPaymentMapper orderPaymentMapper;

    public OrderPaymentServiceImpl(OrderPaymentRepository orderPaymentRepository, OrderPaymentMapper orderPaymentMapper) {
        this.orderPaymentRepository = orderPaymentRepository;
        this.orderPaymentMapper = orderPaymentMapper;
    }

    /**
     * Save a orderPayment.
     *
     * @param orderPaymentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderPaymentDTO save(OrderPaymentDTO orderPaymentDTO) {
        log.debug("Request to save OrderPayment : {}", orderPaymentDTO);

        OrderPayment orderPayment = orderPaymentMapper.toEntity(orderPaymentDTO);
        orderPayment = orderPaymentRepository.save(orderPayment);
        return orderPaymentMapper.toDto(orderPayment);
    }

    /**
     * Get all the orderPayments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderPayments");
        return orderPaymentRepository.findAll(pageable)
            .map(orderPaymentMapper::toDto);
    }


    /**
     * Get one orderPayment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderPaymentDTO> findOne(Long id) {
        log.debug("Request to get OrderPayment : {}", id);
        return orderPaymentRepository.findById(id)
            .map(orderPaymentMapper::toDto);
    }

    /**
     * Delete the orderPayment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderPayment : {}", id);
        orderPaymentRepository.deleteById(id);
    }
}
