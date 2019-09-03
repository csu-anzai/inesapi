package id.co.indoeskrim.repository;

import id.co.indoeskrim.domain.OrderShipping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderShipping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping, Long> {

}
