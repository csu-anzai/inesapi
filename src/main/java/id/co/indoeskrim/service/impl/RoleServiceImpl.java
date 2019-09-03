package id.co.indoeskrim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Authority;
import id.co.indoeskrim.domain.Menu;
import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.domain.Role;
import id.co.indoeskrim.repository.AuthorityRepository;
import id.co.indoeskrim.repository.MenuRepository;
import id.co.indoeskrim.repository.MenuRoleRepository;
import id.co.indoeskrim.repository.RoleRepository;
import id.co.indoeskrim.service.RoleService;
import id.co.indoeskrim.service.dto.MenuRequestDTO;
import id.co.indoeskrim.service.dto.MenuResponseDTO;
import id.co.indoeskrim.service.dto.RoleDTO;
import id.co.indoeskrim.service.dto.RoleMenuRequestDTO;
import id.co.indoeskrim.service.mapper.MenuResponseMapper;
import id.co.indoeskrim.service.mapper.RoleMapper;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	private static final String ENTITY_NAME = "role";

	private final RoleMapper roleMapper;
	private final RoleRepository roleRepository;
	private final AuthorityRepository authorityRepository;
	private final AuthorityMenuService authorityMenuService;
	private final MenuRoleRepository menuRoleRepository;
	private final MenuRepository menuRepository;
	private final MenuResponseMapper menuResponseMapper;
	
	public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository,
			AuthorityRepository authorityRepository,
			AuthorityMenuService authorityMenuService,
			MenuRoleRepository menuRoleRepository,
			MenuRepository menuRepository,
			MenuResponseMapper menuResponseMapper) {
		this.roleMapper = roleMapper;
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;
		this.authorityMenuService = authorityMenuService;
		this.menuRoleRepository = menuRoleRepository;
		this.menuRepository = menuRepository;
		this.menuResponseMapper =menuResponseMapper;
	}
	
	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		 log.debug("Request to save Warehouse : {}", roleDTO);

		 Boolean check = validate(roleDTO.getRoleCode());
		 if(check) {
			 throw new BadRequestAlertException("Role code already exist", ENTITY_NAME, "codeExist");
		 }
		 
		 Role role = roleMapper.toEntity(roleDTO);
		 
		 role = roleRepository.save(role);
		 
		 Boolean checkAuthority = validateAuthority(roleDTO.getRoleCode());
		 if(checkAuthority) {
			 throw new BadRequestAlertException("Authority already exist", ENTITY_NAME, "codeExist");
		 }
		 Authority authority = new Authority();
		 authority.setName(role.getRoleCode());
		 authorityRepository.save(authority);		 
	     return roleMapper.toDto(role);
	}

	private Boolean validateAuthority(String roleCode) {
		Optional<Authority> authority = authorityRepository.findById(roleCode);
		if(authority.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	private Boolean validate(String roleCode) {
		Optional<Role> role = roleRepository.findByRoleCode(roleCode);
		if(role.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		Optional<Role> roles = roleRepository.findByRoleCode(roleDTO.getRoleCode());
		if(!roles.isPresent()) {
			throw new BadRequestAlertException("Role code not exist", ENTITY_NAME, "codeNotExist");
		}
		
		Role role = roleMapper.toEntity(roleDTO);
		role = roleRepository.save(role);
	    return roleMapper.toDto(role);
	}

	@Override
	public Page<RoleDTO> findAll(Pageable pageable) {
		Page<RoleDTO> results = roleRepository.findAll(pageable).map(roleMapper::toDto);
		return results;
	}

	@Override
	public Optional<RoleDTO> findByRoleCode(String roleCode) {
		Optional<RoleDTO> result = roleRepository.findByRoleCode(roleCode).map(roleMapper::toDto);
		return result;
	}

	@Override
	public List<MenuResponseDTO> menuList(Optional<String> roleName) {
		List<MenuResponseDTO> menuList = new ArrayList<MenuResponseDTO>();
		
		List<Menu> menus = menuRepository.findByParentIdIsNullAndIsDeletedFalseOrderByOrderAsc();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		if(roleName.isPresent()) {
			List<MenuRole> menuRoleList = menuRoleRepository.findByRoleName(roleName.get());
			List<Long> menuIds = menuRoleList.stream()
					.map(menuRole->{return menuRole.getMenuId().longValue();})
					.collect(Collectors.toList());
			List<Menu> registeredMenu = menuRepository.findByMenuIdInAndIsDeletedFalseOrderByOrderAsc(menuIds);
			for(Menu menu : registeredMenu) {
				map.put(menu.getMenuId(), menu.getName());
			}	
		}
		
		
		List<MenuResponseDTO> newMenuList = new ArrayList<MenuResponseDTO>();
		for (Menu menu : menus) {
			MenuResponseDTO menuResponseDTO = menuResponseMapper.toDto(menu);
			if(map.containsKey(menu.getMenuId())){
				menuResponseDTO.setIsRegister(1);
			} else {
				menuResponseDTO.setIsRegister(0);
			}
			//depth 2
			List<MenuResponseDTO> newMenuList2 = new ArrayList<MenuResponseDTO>();
			for (Menu menu2 : menu.getMenus()) {
				if(!menu2.getIsDeleted()) {
					MenuResponseDTO menuResponseDTO2 = menuResponseMapper.toDto(menu2);
					
					if(map.containsKey(menu2.getMenuId())) {
						menuResponseDTO2.setIsRegister(1);
					} else {
						menuResponseDTO2.setIsRegister(0);
					}	
					//depth 3
					List<MenuResponseDTO> newMenuList3 = new ArrayList<MenuResponseDTO>();
					for (Menu menu3 : menu2.getMenus()) {
						if(!menu3.getIsDeleted()) {
							MenuResponseDTO menuResponseDTO3 = menuResponseMapper.toDto(menu3);
							if(map.containsKey(menu3.getMenuId())) {
								menuResponseDTO3.setIsRegister(1);
							} else {
								menuResponseDTO3.setIsRegister(0);
							}
							//depth 3
							List<MenuResponseDTO> newMenuList4 = new ArrayList<MenuResponseDTO>();
							for(Menu menu4 : menu3.getMenus()) {
								MenuResponseDTO menuResponseDTO4 = menuResponseMapper.toDto(menu4);
								if(map.containsKey(menu4.getMenuId())) {
									menuResponseDTO4.setIsRegister(1);
								} else {
									menuResponseDTO4.setIsRegister(0);
								}
								newMenuList4.add(menuResponseDTO4);
							}
							menuResponseDTO3.setMenus(newMenuList4);
							newMenuList3.add(menuResponseDTO3);
						}
					}
					menuResponseDTO2.setMenus(newMenuList3);
					newMenuList2.add(menuResponseDTO2);
				}
			}
			menuResponseDTO.setMenus(newMenuList2);
			newMenuList.add(menuResponseDTO);
		}
		
		menuList.addAll(newMenuList);
		return menuList;
	}

	@Override
	public RoleMenuRequestDTO createRoleMenu(RoleMenuRequestDTO roleMenuRequestDTO) {
		List<MenuRole> list = menuRoleRepository.findByRoleName(roleMenuRequestDTO.getRoleCode());
		for(MenuRole mr : list) {
			menuRoleRepository.delete(mr);
		}
		Authority authority = new Authority();
		authority.setName(roleMenuRequestDTO.getRoleCode());
		for(MenuRequestDTO mrd : roleMenuRequestDTO.getListMenu()) {
			MenuRole menuRole = new MenuRole();
			menuRole.setAuthority(authority);
			menuRole.setMenuId(mrd.getMenuId());
			menuRole.setRoleName(roleMenuRequestDTO.getRoleCode());
			menuRole = menuRoleRepository.save(menuRole);
		}
		
		return roleMenuRequestDTO;
	}

	
}

