package id.co.indoeskrim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Menu;
import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.repository.MenuRepository;
import id.co.indoeskrim.service.MenuRoleService;
import id.co.indoeskrim.service.MenuService;
import id.co.indoeskrim.service.dto.MenuDTO;
import id.co.indoeskrim.service.dto.MenuRoleDTO;
import id.co.indoeskrim.service.mapper.MenuMapper;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private MenuRepository menuRepository;

    private MenuMapper menuMapper;
    
    @Autowired
    private MenuRoleService menuRoleService;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save Menu : {}", menuDTO);

        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        
        //save menu roles
        if(menuDTO.getMenuId() != null) {
        	List<Long> deletedAuth = new ArrayList<>();
        	List<String> addedAuth = new ArrayList<>();
        	List<MenuRole> oldMenuRoleList = menuRoleService.findAuthListByMenuId(menuDTO.getMenuId());
        	List<String> oldAuthorityList = new ArrayList<String>();
        	for (MenuRole oldMenuRole : oldMenuRoleList) {
        		oldAuthorityList.add(oldMenuRole.getAuthority().getName());
				if(!menuDTO.getAuthorities().contains(oldMenuRole.getAuthority().getName())) {
					deletedAuth.add(oldMenuRole.getMenuRoleId());
					//delete auth
					menuRoleService.delete(oldMenuRole.getMenuRoleId());
				}
			}
        	for (String authority : menuDTO.getAuthorities()) {
				if(!oldAuthorityList.contains(authority)) {
					addedAuth.add(authority);
					//add auth
					MenuRoleDTO menuRole = new MenuRoleDTO();
					menuRole.setMenuId(menu.getMenuId().intValue());
					menuRole.setRoleName(authority);
					menuRoleService.save(menuRole);
				}
			}
        }else {
        	//register all menu roles
        	for (String authority : menuDTO.getAuthorities()) {
				MenuRoleDTO menuRole = new MenuRoleDTO();
				menuRole.setMenuId(menu.getMenuId().intValue());
				menuRole.setRoleName(authority);
				menuRoleService.save(menuRole);
			}
        }
        
        return null;
    }

    /**
     * Get all the menus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> findAllByParentIdNull() {
        log.debug("Request to get all Menus");
        return menuMapper.toDto(menuRepository.findAllByParentIdNullOrderByOrderAsc());
    }
    
    /**
     * Get all the menus.
     *
     * @param
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuDTO> findAll() {
        log.debug("Request to get all Menus");
        return menuMapper.toDto(menuRepository.findAll());
    }
    
    /**
     * Get all the menus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Menus");
        return menuRepository.findAll(pageable)
            .map(menuMapper::toDto);
    }


    /**
     * Get one menu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(Long id) {
        log.debug("Request to get Menu : {}", id);
        Optional<MenuDTO> menuDTO = menuRepository.findById(id)
                .map(menuMapper::toDto);
        if(menuDTO.isPresent()) {
        	List<String> authorities = new ArrayList<String>();
        	List<MenuRole> menuRoleList = menuRoleService.findAuthListByMenuId(menuDTO.get().getMenuId());
        	for (MenuRole menuRole : menuRoleList) {
        		authorities.add(menuRole.getAuthority().getName());
			}
        	menuDTO.get().setAuthorities(authorities);
        }
        return menuDTO;
    }

    /**
     * Delete the menu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Menu : {}", id);
        menuRepository.deleteById(id);
    }

	@Override
	public void bulkUpdate(List<MenuDTO> menus) {
		for (MenuDTO menuDTO : menus) {
			if (menuDTO.getMenuId() == null) {
	            throw new BadRequestAlertException("Invalid id", "menu", "idnull");
	        }
			menuRepository.updateParentAndOrder(menuMapper.toEntity(menuDTO));
			
		}
	}
}
