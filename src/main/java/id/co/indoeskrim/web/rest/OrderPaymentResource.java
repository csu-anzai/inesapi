package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.service.OrderPaymentService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.OrderPaymentDTO;
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
 * REST controller for managing OrderPayment.
 */
@RestController
@RequestMapping("/api")
public class OrderPaymentResource {

    private final Logger log = LoggerFactory.getLogger(OrderPaymentResource.class);

    private static final String ENTITY_NAME = "orderPayment";

    private final OrderPaymentService orderPaymentService;

    public OrderPaymentResource(OrderPaymentService orderPaymentService) {
        this.orderPaymentService = orderPaymentService;
    }

    /**
     * POST  /order-payments : Create a new orderPayment.
     *
     * @param orderPaymentDTO the orderPaymentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderPaymentDTO, or with status 400 (Bad Request) if the orderPayment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/order-payments")
//    public ResponseEntity<OrderPaymentDTO> createOrderPayment(@Valid @RequestBody OrderPaymentDTO orderPaymentDTO) throws URISyntaxException {
//        log.debug("REST request to save OrderPayment : {}", orderPaymentDTO);
//        if (orderPaymentDTO.getId() != null) {
//            throw new BadRequestAlertException("A new orderPayment cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        OrderPaymentDTO result = orderPaymentService.save(orderPaymentDTO);
//        return ResponseEntity.created(new URI("/api/order-payments/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /order-payments : Updates an existing orderPayment.
     *
     * @param orderPaymentDTO the orderPaymentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderPaymentDTO,
     * or with status 400 (Bad Request) if the orderPaymentDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderPaymentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/order-payments")
//    public ResponseEntity<OrderPaymentDTO> updateOrderPayment(@Valid @RequestBody OrderPaymentDTO orderPaymentDTO) throws URISyntaxException {
//        log.debug("REST request to update OrderPayment : {}", orderPaymentDTO);
//        if (orderPaymentDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        OrderPaymentDTO result = orderPaymentService.save(orderPaymentDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderPaymentDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /order-payments : get all the orderPayments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderPayments in body
     */
//    @GetMapping("/order-payments")
//    public ResponseEntity<List<OrderPaymentDTO>> getAllOrderPayments(Pageable pageable) {
//        log.debug("REST request to get a page of OrderPayments");
//        Page<OrderPaymentDTO> page = orderPaymentService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-payments");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    /**
     * GET  /order-payments/:id : get the "id" orderPayment.
     *
     * @param id the id of the orderPaymentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderPaymentDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/order-payments/{id}")
//    public ResponseEntity<OrderPaymentDTO> getOrderPayment(@PathVariable Long id) {
//        log.debug("REST request to get OrderPayment : {}", id);
//        Optional<OrderPaymentDTO> orderPaymentDTO = orderPaymentService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(orderPaymentDTO);
//    }

    /**
     * DELETE  /order-payments/:id : delete the "id" orderPayment.
     *
     * @param id the id of the orderPaymentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/order-payments/{id}")
//    public ResponseEntity<Void> deleteOrderPayment(@PathVariable Long id) {
//        log.debug("REST request to delete OrderPayment : {}", id);
//        orderPaymentService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
