package id.co.indoeskrim.web.rest;
import id.co.indoeskrim.service.CustomerAddressService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.CustomerAddressDTO;
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
 * REST controller for managing CustomerAddress.
 */
@RestController
@RequestMapping("/api")
public class CustomerAddressResource {

    private final Logger log = LoggerFactory.getLogger(CustomerAddressResource.class);

    private static final String ENTITY_NAME = "customerAddress";

    private final CustomerAddressService customerAddressService;

    public CustomerAddressResource(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    /**
     * POST  /customer-addresses : Create a new customerAddress.
     *
     * @param customerAddressDTO the customerAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerAddressDTO, or with status 400 (Bad Request) if the customerAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-addresses")
    public ResponseEntity<CustomerAddressDTO> createCustomerAddress(@Valid @RequestBody CustomerAddressDTO customerAddressDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerAddress : {}", customerAddressDTO);
        if (customerAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerAddressDTO result = customerAddressService.save(customerAddressDTO);
        return ResponseEntity.created(new URI("/api/customer-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-addresses : Updates an existing customerAddress.
     *
     * @param customerAddressDTO the customerAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerAddressDTO,
     * or with status 400 (Bad Request) if the customerAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerAddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-addresses")
    public ResponseEntity<CustomerAddressDTO> updateCustomerAddress(@Valid @RequestBody CustomerAddressDTO customerAddressDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerAddress : {}", customerAddressDTO);
        if (customerAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerAddressDTO result = customerAddressService.save(customerAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-addresses : get all the customerAddresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customerAddresses in body
     */
    @GetMapping("/customer-addresses")
    public ResponseEntity<List<CustomerAddressDTO>> getAllCustomerAddresses(Pageable pageable) {
        log.debug("REST request to get a page of CustomerAddresses");
        Page<CustomerAddressDTO> page = customerAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-addresses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /customer-addresses/:id : get the "id" customerAddress.
     *
     * @param id the id of the customerAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-addresses/{id}")
    public ResponseEntity<CustomerAddressDTO> getCustomerAddress(@PathVariable Long id) {
        log.debug("REST request to get CustomerAddress : {}", id);
        Optional<CustomerAddressDTO> customerAddressDTO = customerAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerAddressDTO);
    }

    /**
     * DELETE  /customer-addresses/:id : delete the "id" customerAddress.
     *
     * @param id the id of the customerAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/customer-addresses/{id}")
//    public ResponseEntity<Void> deleteCustomerAddress(@PathVariable Long id) {
//        log.debug("REST request to delete CustomerAddress : {}", id);
//        customerAddressService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
