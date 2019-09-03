package id.co.indoeskrim.service;

import id.co.indoeskrim.service.dto.OrderShippingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrderShipping.
 */
public interface OrderShippingService {

    /**
     * Save a orderShipping.
     *
     * @param orderShippingDTO the entity to save
     * @return the persisted entity
     */
    OrderShippingDTO save(OrderShippingDTO orderShippingDTO);

    /**
     * Get all the orderShippings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderShippingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderShipping.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderShippingDTO> findOne(Long id);

    /**
     * Delete the "id" orderShipping.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
