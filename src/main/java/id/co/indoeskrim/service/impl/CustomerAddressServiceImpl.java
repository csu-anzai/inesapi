package id.co.indoeskrim.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.CustomerAddress;
import id.co.indoeskrim.domain.MasterAddress;
import id.co.indoeskrim.repository.CustomerAddressRepository;
import id.co.indoeskrim.repository.MasterAddressRepository;
import id.co.indoeskrim.service.CustomerAddressService;
import id.co.indoeskrim.service.dto.CustomerAddressDTO;
import id.co.indoeskrim.service.mapper.CustomerAddressMapper;

/**
 * Service Implementation for managing CustomerAddress.
 */
@Service
@Transactional
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final Logger log = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);

    private final CustomerAddressRepository customerAddressRepository;
    private final MasterAddressRepository masterAddressRepository;

    private final CustomerAddressMapper customerAddressMapper;

    public CustomerAddressServiceImpl(CustomerAddressRepository customerAddressRepository, MasterAddressRepository masterAddressRepository, CustomerAddressMapper customerAddressMapper) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerAddressMapper = customerAddressMapper;
        this.masterAddressRepository = masterAddressRepository;
    }

    /**
     * Save a customerAddress.
     *
     * @param customerAddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerAddressDTO save(CustomerAddressDTO customerAddressDTO) {
        log.debug("Request to save CustomerAddress : {}", customerAddressDTO);
        CustomerAddress customerAddress = customerAddressMapper.toEntity(customerAddressDTO);
        Optional<MasterAddress> msAddr = masterAddressRepository.findById(customerAddressDTO.getAddressId());
        customerAddress.setMasterAddress(msAddr.get());
        customerAddress = customerAddressRepository.saveAndFlush(customerAddress);
        return customerAddressMapper.toDto(customerAddress);
    }

    /**
     * Get all the customerAddresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerAddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerAddresses");
        return customerAddressRepository.findAll(pageable)
            .map(customerAddressMapper::toDto);
    }


    /**
     * Get one customerAddress by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerAddressDTO> findOne(Long id) {
        log.debug("Request to get CustomerAddress : {}", id);
        return customerAddressRepository.findById(id)
            .map(customerAddressMapper::toDto);
    }

    /**
     * Delete the customerAddress by id.
     *
     * @param id the id of the entity
     */
//    @Override
//    public void delete(Long id) {
//        log.debug("Request to delete CustomerAddress : {}", id);
//        customerAddressRepository.deleteById(id);
//    }
}
