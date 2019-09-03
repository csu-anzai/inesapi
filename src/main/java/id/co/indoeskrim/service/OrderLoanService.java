package id.co.indoeskrim.service;

import id.co.indoeskrim.service.dto.OrderLoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrderLoan.
 */
public interface OrderLoanService {

    /**
     * Save a orderLoan.
     *
     * @param orderLoanDTO the entity to save
     * @return the persisted entity
     */
    OrderLoanDTO save(OrderLoanDTO orderLoanDTO);

    /**
     * Get all the orderLoans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderLoanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderLoan.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderLoanDTO> findOne(Long id);

    /**
     * Delete the "id" orderLoan.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
