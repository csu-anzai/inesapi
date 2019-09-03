package id.co.indoeskrim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.Role;

@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	Optional<Role> findByRoleCode(String roleCode);
	Optional<Role> findByRoleCodeAndIsActive(String roleCode, Integer isActive);
}
