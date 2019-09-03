package id.co.indoeskrim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.service.dto.MenuDTO;

/**
 * Service Interface for managing Menu.
 */
public interface MenuService {

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    MenuDTO save(MenuDTO menuDTO);

    /**
     * Get all the menus.
     *
     * @param 
     * @return the list of entities
     */
    List<MenuDTO> findAll();
    
    /**
     * Get all the menus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MenuDTO> findAll(Pageable pageable);
    
    /**
     * Get all the menus by parentId is null.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    List<MenuDTO> findAllByParentIdNull();


    /**
     * Get the "id" menu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MenuDTO> findOne(Long id);

    /**
     * Delete the "id" menu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    //bulk update menu
    void bulkUpdate(List<MenuDTO> menus);
}
