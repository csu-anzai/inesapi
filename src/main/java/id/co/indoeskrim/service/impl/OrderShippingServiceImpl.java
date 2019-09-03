package id.co.indoeskrim.service.impl;

import id.co.indoeskrim.service.OrderShippingService;
import id.co.indoeskrim.domain.OrderShipping;
import id.co.indoeskrim.repository.OrderShippingRepository;
import id.co.indoeskrim.service.dto.OrderShippingDTO;
import id.co.indoeskrim.service.mapper.OrderShippingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OrderShipping.
 */
@Service
@Transactional
public class OrderShippingServiceImpl implements OrderShippingService {

    private final Logger log = LoggerFactory.getLogger(OrderShippingServiceImpl.class);

    private final OrderShippingRepository orderShippingRepository;

    private final OrderShippingMapper orderShippingMapper;

    public OrderShippingServiceImpl(OrderShippingRepository orderShippingRepository, OrderShippingMapper orderShippingMapper) {
        this.orderShippingRepository = orderShippingRepository;
        this.orderShippingMapper = orderShippingMapper;
    }

    /**
     * Save a orderShipping.
     *
     * @param orderShippingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderShippingDTO save(OrderShippingDTO orderShippingDTO) {
        log.debug("Request to save OrderShipping : {}", orderShippingDTO);

        OrderShipping orderShipping = orderShippingMapper.toEntity(orderShippingDTO);
        orderShipping = orderShippingRepository.save(orderShipping);
        return orderShippingMapper.toDto(orderShipping);
    }

    /**
     * Get all the orderShippings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderShippingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderShippings");
        return orderShippingRepository.findAll(pageable)
            .map(orderShippingMapper::toDto);
    }


    /**
     * Get one orderShipping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderShippingDTO> findOne(Long id) {
        log.debug("Request to get OrderShipping : {}", id);
        return orderShippingRepository.findById(id)
            .map(orderShippingMapper::toDto);
    }

    /**
     * Delete the orderShipping by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderShipping : {}", id);
        orderShippingRepository.deleteById(id);
    }
}
