package id.co.indoeskrim.web.rest;

import id.co.indoeskrim.service.OrderMasterService;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import id.co.indoeskrim.service.dto.OrderMasterDTO;
import id.co.indoeskrim.service.dto.req.OrderReqDTO;
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
 * REST controller for managing OrderMaster.
 */
@RestController
@RequestMapping("/api")
public class OrderMasterResource {

    private final Logger log = LoggerFactory.getLogger(OrderMasterResource.class);

    private static final String ENTITY_NAME = "orderMaster";

    private final OrderMasterService orderMasterService;

    public OrderMasterResource(OrderMasterService orderMasterService) {
        this.orderMasterService = orderMasterService;
    }

    /**
     * POST  /order-masters : Create a new orderMaster.
     *
     * @param orderMasterDTO the orderMasterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderMasterDTO, or with status 400 (Bad Request) if the orderMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/order-masters")
//    public ResponseEntity<OrderMasterDTO> createOrderMaster(@Valid @RequestBody OrderMasterDTO orderMasterDTO) throws URISyntaxException {
//        log.debug("REST request to save OrderMaster : {}", orderMasterDTO);
//        if (orderMasterDTO.getOrderId() != null) {
//            throw new BadRequestAlertException("A new orderMaster cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        OrderMasterDTO result = orderMasterService.save(orderMasterDTO);
//        return ResponseEntity.created(new URI("/api/order-masters/" + result.getOrderId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getOrderId()))
//            .body(result);
//    }

    /**
     * PUT  /order-masters : Updates an existing orderMaster.
     *
     * @param orderMasterDTO the orderMasterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderMasterDTO,
     * or with status 400 (Bad Request) if the orderMasterDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderMasterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/order-masters")
//    public ResponseEntity<OrderMasterDTO> updateOrderMaster(@Valid @RequestBody OrderMasterDTO orderMasterDTO) throws URISyntaxException {
//        log.debug("REST request to update OrderMaster : {}", orderMasterDTO);
//        if (orderMasterDTO.getOrderId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        OrderMasterDTO result = orderMasterService.save(orderMasterDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderMasterDTO.getOrderId()))
//            .body(result);
//    }

    /**
     * GET  /order-masters : get all the orderMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderMasters in body
     */
//    @GetMapping("/order-masters")
//    public ResponseEntity<List<OrderMasterDTO>> getAllOrderMasters(Pageable pageable) {
//        log.debug("REST request to get a page of OrderMasters");
//        Page<OrderMasterDTO> page = orderMasterService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-masters");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    /**
     * GET  /order-masters/:id : get the "id" orderMaster.
     *
     * @param id the id of the orderMasterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderMasterDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/order-masters/{orderId}")
//    public ResponseEntity<OrderMasterDTO> getOrderMaster(@PathVariable String orderId) {
//        log.debug("REST request to get OrderMaster : {}", orderId);
//        Optional<OrderMasterDTO> orderMasterDTO = orderMasterService.findOne(orderId);
//        return ResponseUtil.wrapOrNotFound(orderMasterDTO);
//    }

    /**
     * DELETE  /order-masters/:id : delete the "id" orderMaster.
     *
     * @param id the id of the orderMasterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/order-masters/{orderId}")
//    public ResponseEntity<Void> deleteOrderMaster(@PathVariable String orderId) {
//        log.debug("REST request to delete OrderMaster : {}", orderId);
//        orderMasterService.delete(orderId);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, orderId)).build();
//    }

    @PostMapping("/create-order/")
    public ResponseEntity<OrderMasterDTO> createOrder(@Valid @RequestBody OrderReqDTO orderReqDTO) throws URISyntaxException {
        log.debug("REST request to save Order : {}", orderReqDTO);
        if (orderReqDTO.getOrderId() != null) {
            throw new BadRequestAlertException("A new orderMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderMasterDTO result = orderMasterService.createOrder(orderReqDTO);
        return ResponseEntity.created(new URI("/api/create-order/" + result.getOrderId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getOrderId()))
                .body(result);
    }

    @GetMapping("/order-masters/{orderId}")
    public ResponseEntity<OrderReqDTO> getOrder(@PathVariable String orderId) {
        log.debug("REST request to get Order : {}", orderId);
        OrderReqDTO result = orderMasterService.getOrder(orderId);
        return ResponseEntity.ok()
        		.headers(HeaderUtil.createAlert(ENTITY_NAME, orderId))
        		.body(result);
    }

    @PostMapping("/update-order-status-paid/{orderId}")
    public ResponseEntity<OrderMasterDTO> updateOrderStatusPaid(@Valid @RequestBody String orderId) throws URISyntaxException {
    	log.debug("REST request to update Order : {}", orderId);
        OrderMasterDTO result = orderMasterService.updateOrderStatusPaid(orderId);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderId))
                .body(result);
    }
}
