package id.co.indoeskrim.service;

import id.co.indoeskrim.service.dto.OrderPaymentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrderPayment.
 */
public interface OrderPaymentService {

    /**
     * Save a orderPayment.
     *
     * @param orderPaymentDTO the entity to save
     * @return the persisted entity
     */
    OrderPaymentDTO save(OrderPaymentDTO orderPaymentDTO);

    /**
     * Get all the orderPayments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderPaymentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderPayment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderPaymentDTO> findOne(Long id);

    /**
     * Delete the "id" orderPayment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
