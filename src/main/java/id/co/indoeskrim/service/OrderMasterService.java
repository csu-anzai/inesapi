package id.co.indoeskrim.service;

import id.co.indoeskrim.service.dto.OrderMasterDTO;
import id.co.indoeskrim.service.dto.req.OrderReqDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrderMaster.
 */
public interface OrderMasterService {

    /**
     * Save a orderMaster.
     *
     * @param orderMasterDTO the entity to save
     * @return the persisted entity
     */
    OrderMasterDTO save(OrderMasterDTO orderMasterDTO);

    /**
     * Get all the orderMasters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderMasterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" orderMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderMasterDTO> findOne(String orderId);

    /**
     * Delete the "id" orderMaster.
     *
     * @param id the id of the entity
     */
    void delete(String orderId);

    OrderMasterDTO createOrder(OrderReqDTO orderReqDTO);

	OrderReqDTO getOrder(String orderId);

    OrderMasterDTO updateOrderStatusPaid(String orderId);
}
