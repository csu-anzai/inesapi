package id.co.indoeskrim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.CustomerAddress;


/**
 * Spring Data  repository for the CustomerAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
