package id.co.indoeskrim.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.MenuRole;


/**
 * Spring Data  repository for the MenuRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRoleRepository extends JpaRepository<MenuRole, Long> {
	
	List<MenuRole> findByRoleName(String roleName);
	List<MenuRole> findByMenuId(Integer menuId);
}
