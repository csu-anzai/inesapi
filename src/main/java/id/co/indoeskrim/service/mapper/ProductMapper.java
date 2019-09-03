package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.Product;
import id.co.indoeskrim.service.dto.ProductDTO;

/**
 * Mapper for the entity Product and its DTO ProductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    default Product fromId(String id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setProductCode(id);
        return product;
    }
}
