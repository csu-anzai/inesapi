package id.co.indoeskrim.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.indoeskrim.service.ProductService;
import id.co.indoeskrim.service.dto.ProductDTO;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDTO);
        if (productDTO.getProductCode() != null) {
            throw new BadRequestAlertException("A new product cannot already have an code", ENTITY_NAME, "codeexists");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getProductCode()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getProductCode()))
            .body(result);
    }

    
    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productDTO);
        if (productDTO.getProductCode() == null) {
            throw new BadRequestAlertException("Invalid code", ENTITY_NAME, "codenull");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDTO.getProductCode()))
            .body(result);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get a page of Product");
        Page<ProductDTO> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/products/{productCode}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productCode) {
        log.debug("REST request to get Product : {}", productCode);
        Optional<ProductDTO> productDTO = productService.findOne(productCode);
        return ResponseUtil.wrapOrNotFound(productDTO);
    }
}
