package id.co.indoeskrim.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.service.dto.ProductDTO;

/**
 * Service Interface for managing Product.
 */
public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    Page<ProductDTO> findAll(Pageable pageable);

    Optional<ProductDTO> findOne(String productCode);
}
