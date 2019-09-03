package id.co.indoeskrim.repository;

import id.co.indoeskrim.domain.OrderPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {

}
