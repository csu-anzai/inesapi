package id.co.indoeskrim.web.rest;
import id.co.indoeskrim.service.CustomerService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.CustomerDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * POST  /customers : Create a new customer.
     *
     * @param customerDTO the customerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerDTO, or with status 400 (Bad Request) if the customer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDTO);
        if (customerDTO.getCustomerId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/customers/" + result.getCustomerId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getCustomerId().toString()))
            .body(result);
    }

    /**
     * PUT  /customers : Updates an existing customer.
     *
     * @param customerDTO the customerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerDTO,
     * or with status 400 (Bad Request) if the customerDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customers")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customerDTO);
        if (customerDTO.getCustomerId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerDTO.getCustomerId().toString()))
            .body(result);
    }

    /**
     * GET  /customers : get all the customers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageable) {
        log.debug("REST request to get a page of Customers");
        Page<CustomerDTO> page = customerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /customers/:id : get the "id" customer.
     *
     * @param id the id of the customerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
        log.debug("REST request to get Customer : {}", customerId);
        Optional<CustomerDTO> customerDTO = customerService.findOne(customerId);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    /**
     * DELETE  /customers/:id : delete the "id" customer.
     *
     * @param id the id of the customerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
//        log.debug("REST request to delete Customer : {}", customerId);
//        customerService.delete(customerId);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, customerId.toString())).build();
//    }
}
