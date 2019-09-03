package id.co.indoeskrim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.Product;


/**
 * Spring Data  repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	
	Optional<Product> findByProductCode(String productCode);
	
	@Query(value = "SELECT {h-schema}get_product_code()", nativeQuery = true)
	String getProductCode();
	
	boolean existsByProductCode(String itemCode);
}
