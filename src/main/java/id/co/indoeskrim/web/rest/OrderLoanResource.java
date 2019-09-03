package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.service.OrderLoanService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.OrderLoanDTO;
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
 * REST controller for managing OrderLoan.
 */
@RestController
@RequestMapping("/api")
public class OrderLoanResource {

    private final Logger log = LoggerFactory.getLogger(OrderLoanResource.class);

    private static final String ENTITY_NAME = "orderLoan";

    private final OrderLoanService orderLoanService;

    public OrderLoanResource(OrderLoanService orderLoanService) {
        this.orderLoanService = orderLoanService;
    }

    /**
     * POST  /order-loans : Create a new orderLoan.
     *
     * @param orderLoanDTO the orderLoanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderLoanDTO, or with status 400 (Bad Request) if the orderLoan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/order-loans")
//    public ResponseEntity<OrderLoanDTO> createOrderLoan(@Valid @RequestBody OrderLoanDTO orderLoanDTO) throws URISyntaxException {
//        log.debug("REST request to save OrderLoan : {}", orderLoanDTO);
//        if (orderLoanDTO.getId() != null) {
//            throw new BadRequestAlertException("A new orderLoan cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        OrderLoanDTO result = orderLoanService.save(orderLoanDTO);
//        return ResponseEntity.created(new URI("/api/order-loans/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /order-loans : Updates an existing orderLoan.
     *
     * @param orderLoanDTO the orderLoanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderLoanDTO,
     * or with status 400 (Bad Request) if the orderLoanDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderLoanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/order-loans")
//    public ResponseEntity<OrderLoanDTO> updateOrderLoan(@Valid @RequestBody OrderLoanDTO orderLoanDTO) throws URISyntaxException {
//        log.debug("REST request to update OrderLoan : {}", orderLoanDTO);
//        if (orderLoanDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        OrderLoanDTO result = orderLoanService.save(orderLoanDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderLoanDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /order-loans : get all the orderLoans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderLoans in body
     */
//    @GetMapping("/order-loans")
//    public ResponseEntity<List<OrderLoanDTO>> getAllOrderLoans(Pageable pageable) {
//        log.debug("REST request to get a page of OrderLoans");
//        Page<OrderLoanDTO> page = orderLoanService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-loans");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    /**
     * GET  /order-loans/:id : get the "id" orderLoan.
     *
     * @param id the id of the orderLoanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderLoanDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/order-loans/{id}")
//    public ResponseEntity<OrderLoanDTO> getOrderLoan(@PathVariable Long id) {
//        log.debug("REST request to get OrderLoan : {}", id);
//        Optional<OrderLoanDTO> orderLoanDTO = orderLoanService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(orderLoanDTO);
//    }

    /**
     * DELETE  /order-loans/:id : delete the "id" orderLoan.
     *
     * @param id the id of the orderLoanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/order-loans/{id}")
//    public ResponseEntity<Void> deleteOrderLoan(@PathVariable Long id) {
//        log.debug("REST request to delete OrderLoan : {}", id);
//        orderLoanService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
