package id.co.indoeskrim.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.co.indoeskrim.service.dto.MenuTypeDTO;

/**
 * Service Interface for managing MenuType.
 */
public interface MenuTypeService {

    /**
     * Save a menuType.
     *
     * @param menuTypeDTO the entity to save
     * @return the persisted entity
     */
    MenuTypeDTO save(MenuTypeDTO menuTypeDTO);

    /**
     * Get all the menuTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MenuTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" menuType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MenuTypeDTO> findOne(Long id);

    /**
     * Delete the "id" menuType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
