package id.co.indoeskrim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.service.dto.MenuRoleDTO;

/**
 * Service Interface for managing MenuRole.
 */
public interface MenuRoleService {

    /**
     * Save a menuRole.
     *
     * @param menuRoleDTO the entity to save
     * @return the persisted entity
     */
    MenuRoleDTO save(MenuRoleDTO menuRoleDTO);

    /**
     * Get all the menuRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MenuRoleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" menuRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MenuRoleDTO> findOne(Long id);

    /**
     * Delete the "id" menuRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    public List<MenuRole> findAuthListByMenuId(Long menuId);
}
