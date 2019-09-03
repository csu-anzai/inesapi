package id.co.indoeskrim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.MenuType;


/**
 * Spring Data  repository for the MenuType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {

}
