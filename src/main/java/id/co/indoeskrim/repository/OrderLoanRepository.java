package id.co.indoeskrim.repository;

import id.co.indoeskrim.domain.OrderLoan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLoan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLoanRepository extends JpaRepository<OrderLoan, Long> {

}
