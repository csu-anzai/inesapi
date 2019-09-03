package id.co.indoeskrim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Authority;
import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.repository.MenuRoleRepository;
import id.co.indoeskrim.service.MenuRoleService;
import id.co.indoeskrim.service.dto.MenuRoleDTO;
import id.co.indoeskrim.service.mapper.MenuRoleMapper;

/**
 * Service Implementation for managing MenuRole.
 */
@Service
@Transactional
public class MenuRoleServiceImpl implements MenuRoleService {

    private final Logger log = LoggerFactory.getLogger(MenuRoleServiceImpl.class);

    private MenuRoleRepository menuRoleRepository;

    private MenuRoleMapper menuRoleMapper;

    public MenuRoleServiceImpl(MenuRoleRepository menuRoleRepository, MenuRoleMapper menuRoleMapper) {
        this.menuRoleRepository = menuRoleRepository;
        this.menuRoleMapper = menuRoleMapper;
    }

    /**
     * Save a menuRole.
     *
     * @param menuRoleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuRoleDTO save(MenuRoleDTO menuRoleDTO) {
        log.debug("Request to save MenuRole : {}", menuRoleDTO);

        MenuRole menuRole = menuRoleMapper.toEntity(menuRoleDTO);
        menuRole = menuRoleRepository.save(menuRole);
        return menuRoleMapper.toDto(menuRole);
    }

    /**
     * Get all the menuRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MenuRoles");
        return menuRoleRepository.findAll(pageable)
            .map(menuRoleMapper::toDto);
    }


    /**
     * Get one menuRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuRoleDTO> findOne(Long id) {
        log.debug("Request to get MenuRole : {}", id);
        return menuRoleRepository.findById(id)
            .map(menuRoleMapper::toDto);
    }

    /**
     * Delete the menuRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuRole : {}", id);
        menuRoleRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MenuRole> findAuthListByMenuId(Long menuId) {
        log.debug("Request to get Authority List : {}", menuId);
        List<MenuRole> menuRoleList = menuRoleRepository.findByMenuId(menuId.intValue());
        List<Authority> authorityList = new ArrayList<Authority>();
        for (MenuRole menuRole : menuRoleList) {
        	authorityList.add(menuRole.getAuthority());
		}
        return menuRoleList;
    }
}
