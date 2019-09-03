package id.co.indoeskrim.repository;

import id.co.indoeskrim.domain.OrderMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

	@Query(value = "select * from order_master "
			+ "where order_id = :orderId "
			+ "and order_status = :orderStatus"
			, nativeQuery = true)
	Optional<OrderMaster> findOrderMasterByOrderIdAndOrderStatus(@Param("orderId")String orderId, @Param("orderStatus")String orderStatus);

}
