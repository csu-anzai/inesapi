package id.co.indoeskrim.service.impl;

import id.co.indoeskrim.service.OrderLoanService;
import id.co.indoeskrim.domain.OrderLoan;
import id.co.indoeskrim.repository.OrderLoanRepository;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
import id.co.indoeskrim.service.mapper.OrderLoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OrderLoan.
 */
@Service
@Transactional
public class OrderLoanServiceImpl implements OrderLoanService {

    private final Logger log = LoggerFactory.getLogger(OrderLoanServiceImpl.class);

    private final OrderLoanRepository orderLoanRepository;

    private final OrderLoanMapper orderLoanMapper;

    public OrderLoanServiceImpl(OrderLoanRepository orderLoanRepository, OrderLoanMapper orderLoanMapper) {
        this.orderLoanRepository = orderLoanRepository;
        this.orderLoanMapper = orderLoanMapper;
    }

    /**
     * Save a orderLoan.
     *
     * @param orderLoanDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderLoanDTO save(OrderLoanDTO orderLoanDTO) {
        log.debug("Request to save OrderLoan : {}", orderLoanDTO);

        OrderLoan orderLoan = orderLoanMapper.toEntity(orderLoanDTO);
        orderLoan = orderLoanRepository.save(orderLoan);
        return orderLoanMapper.toDto(orderLoan);
    }

    /**
     * Get all the orderLoans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderLoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderLoans");
        return orderLoanRepository.findAll(pageable)
            .map(orderLoanMapper::toDto);
    }


    /**
     * Get one orderLoan by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrderLoanDTO> findOne(Long id) {
        log.debug("Request to get OrderLoan : {}", id);
        return orderLoanRepository.findById(id)
            .map(orderLoanMapper::toDto);
    }

    /**
     * Delete the orderLoan by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderLoan : {}", id);
        orderLoanRepository.deleteById(id);
    }
}
