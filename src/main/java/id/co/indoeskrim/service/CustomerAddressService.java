package id.co.indoeskrim.service;

import id.co.indoeskrim.service.dto.CustomerAddressDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CustomerAddress.
 */
public interface CustomerAddressService {

    /**
     * Save a customerAddress.
     *
     * @param customerAddressDTO the entity to save
     * @return the persisted entity
     */
    CustomerAddressDTO save(CustomerAddressDTO customerAddressDTO);

    /**
     * Get all the customerAddresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerAddressDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customerAddress.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CustomerAddressDTO> findOne(Long id);

    /**
     * Delete the "id" customerAddress.
     *
     * @param id the id of the entity
     */
//    void delete(Long id);
}
