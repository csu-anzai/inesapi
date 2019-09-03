package id.co.indoeskrim.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Customer;
import id.co.indoeskrim.repository.CustomerRepository;
import id.co.indoeskrim.service.CustomerAddressService;
import id.co.indoeskrim.service.CustomerService;
import id.co.indoeskrim.service.MailService;
import id.co.indoeskrim.service.dto.CustomerDTO;
import id.co.indoeskrim.service.mapper.CustomerMapper;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerAddressService customerAddressService;
    
//    private final UserService userService;
    
    private final MailService mailService;

    public CustomerServiceImpl(
    		CustomerRepository customerRepository, 
    		CustomerMapper customerMapper, 
    		CustomerAddressService customerAddressService,
    		/*UserService userService,*/
    		MailService mailService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerAddressService = customerAddressService;
//        this.userService = userService;
        this.mailService = mailService;
        
    }

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setUserId(customerDTO.getUser().getLogin());
        customer = customerRepository.saveAndFlush(customer);
        
        /**
         * Save Customer Address
         */
//        CustomerAddressDTO newAddress = customerDTO.getCustomerAddress();
//        newAddress.setCustomer(customer);
//        customerAddressService.save(newAddress);
        
        return customerMapper.toDto(customer);
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable)
            .map(customerMapper::toDto);
    }


    /**
     * Get one customer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(String id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id)
            .map(customerMapper::toDto);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity
     */
//    @Override
//    public void delete(String id) {
//        log.debug("Request to delete Customer : {}", id);
//        customerRepository.deleteById(id);
//    }
}
