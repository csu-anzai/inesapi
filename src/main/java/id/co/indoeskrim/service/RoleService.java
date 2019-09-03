package id.co.indoeskrim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.service.dto.MenuResponseDTO;
import id.co.indoeskrim.service.dto.RoleDTO;
import id.co.indoeskrim.service.dto.RoleMenuRequestDTO;

/**
 * Service Interface for managing Warehouse.
 */
public interface RoleService {

    
    RoleDTO save(RoleDTO roleDTO);
    RoleDTO update(RoleDTO roleDTO);
    Page<RoleDTO> findAll(Pageable pageable);
	Optional<RoleDTO> findByRoleCode(String roleCode);
	
	List<MenuResponseDTO> menuList(Optional<String> roleCode);
	
	RoleMenuRequestDTO createRoleMenu(RoleMenuRequestDTO roleMenuRequestDTO);
	
}
