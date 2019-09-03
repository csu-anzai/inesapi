package id.co.indoeskrim.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Product;
import id.co.indoeskrim.repository.ProductRepository;
import id.co.indoeskrim.service.ProductService;
import id.co.indoeskrim.service.dto.ProductDTO;
import id.co.indoeskrim.service.mapper.ProductMapper;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product.setProductCode(productRepository.getProductCode());
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable)
            .map(productMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(String productCode) {
        log.debug("Request to get Product : {}", productCode);
        return productRepository.findById(productCode)
            .map(productMapper::toDto);
    }
}
