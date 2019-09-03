package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.service.OrderShippingService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.OrderShippingDTO;
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
 * REST controller for managing OrderShipping.
 */
@RestController
@RequestMapping("/api")
public class OrderShippingResource {

    private final Logger log = LoggerFactory.getLogger(OrderShippingResource.class);

    private static final String ENTITY_NAME = "orderShipping";

    private final OrderShippingService orderShippingService;

    public OrderShippingResource(OrderShippingService orderShippingService) {
        this.orderShippingService = orderShippingService;
    }

    /**
     * POST  /order-shippings : Create a new orderShipping.
     *
     * @param orderShippingDTO the orderShippingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderShippingDTO, or with status 400 (Bad Request) if the orderShipping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/order-shippings")
//    public ResponseEntity<OrderShippingDTO> createOrderShipping(@Valid @RequestBody OrderShippingDTO orderShippingDTO) throws URISyntaxException {
//        log.debug("REST request to save OrderShipping : {}", orderShippingDTO);
//        if (orderShippingDTO.getId() != null) {
//            throw new BadRequestAlertException("A new orderShipping cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        OrderShippingDTO result = orderShippingService.save(orderShippingDTO);
//        return ResponseEntity.created(new URI("/api/order-shippings/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /order-shippings : Updates an existing orderShipping.
     *
     * @param orderShippingDTO the orderShippingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderShippingDTO,
     * or with status 400 (Bad Request) if the orderShippingDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderShippingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/order-shippings")
//    public ResponseEntity<OrderShippingDTO> updateOrderShipping(@Valid @RequestBody OrderShippingDTO orderShippingDTO) throws URISyntaxException {
//        log.debug("REST request to update OrderShipping : {}", orderShippingDTO);
//        if (orderShippingDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        OrderShippingDTO result = orderShippingService.save(orderShippingDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderShippingDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /order-shippings : get all the orderShippings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderShippings in body
     */
//    @GetMapping("/order-shippings")
//    public ResponseEntity<List<OrderShippingDTO>> getAllOrderShippings(Pageable pageable) {
//        log.debug("REST request to get a page of OrderShippings");
//        Page<OrderShippingDTO> page = orderShippingService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-shippings");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    /**
     * GET  /order-shippings/:id : get the "id" orderShipping.
     *
     * @param id the id of the orderShippingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderShippingDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/order-shippings/{id}")
//    public ResponseEntity<OrderShippingDTO> getOrderShipping(@PathVariable Long id) {
//        log.debug("REST request to get OrderShipping : {}", id);
//        Optional<OrderShippingDTO> orderShippingDTO = orderShippingService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(orderShippingDTO);
//    }

    /**
     * DELETE  /order-shippings/:id : delete the "id" orderShipping.
     *
     * @param id the id of the orderShippingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/order-shippings/{id}")
//    public ResponseEntity<Void> deleteOrderShipping(@PathVariable Long id) {
//        log.debug("REST request to delete OrderShipping : {}", id);
//        orderShippingService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
