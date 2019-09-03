package id.co.indoeskrim.service.impl;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.MenuType;
import id.co.indoeskrim.repository.MenuTypeRepository;
import id.co.indoeskrim.service.MenuTypeService;
import id.co.indoeskrim.service.dto.MenuTypeDTO;
import id.co.indoeskrim.service.mapper.MenuTypeMapper;

/**
 * Service Implementation for managing MenuType.
 */
@Service
@Transactional
public class MenuTypeServiceImpl implements MenuTypeService {

    private final Logger log = LoggerFactory.getLogger(MenuTypeServiceImpl.class);

    private MenuTypeRepository menuTypeRepository;

    private MenuTypeMapper menuTypeMapper;

    public MenuTypeServiceImpl(MenuTypeRepository menuTypeRepository, MenuTypeMapper menuTypeMapper) {
        this.menuTypeRepository = menuTypeRepository;
        this.menuTypeMapper = menuTypeMapper;
    }

    /**
     * Save a menuType.
     *
     * @param menuTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuTypeDTO save(MenuTypeDTO menuTypeDTO) {
        log.debug("Request to save MenuType : {}", menuTypeDTO);

        MenuType menuType = menuTypeMapper.toEntity(menuTypeDTO);
        menuType = menuTypeRepository.save(menuType);
        return menuTypeMapper.toDto(menuType);
    }

    /**
     * Get all the menuTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MenuTypes");
        return menuTypeRepository.findAll(pageable)
            .map(menuTypeMapper::toDto);
    }


    /**
     * Get one menuType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuTypeDTO> findOne(Long id) {
        log.debug("Request to get MenuType : {}", id);
        return menuTypeRepository.findById(id)
            .map(menuTypeMapper::toDto);
    }

    /**
     * Delete the menuType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuType : {}", id);
        menuTypeRepository.deleteById(id);
    }
}
