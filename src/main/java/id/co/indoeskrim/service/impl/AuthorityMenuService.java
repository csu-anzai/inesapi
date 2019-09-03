package id.co.indoeskrim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import id.co.indoeskrim.domain.Menu;
import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.domain.Role;
import id.co.indoeskrim.repository.MenuRepository;
import id.co.indoeskrim.repository.MenuRoleRepository;
import id.co.indoeskrim.repository.RoleRepository;
import id.co.indoeskrim.service.MenuService;
import id.co.indoeskrim.service.dto.MenuDTO;
import id.co.indoeskrim.service.mapper.MenuMapper;
import id.co.indoeskrim.service.util.MenuServiceContainer;

/**
 * 
 * @author Cipta.Mahdiar
 *
 */

@Component
public class AuthorityMenuService extends MenuServiceContainer<Page<MenuDTO>, Map<String, Object>>{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String AUTH_CONTEXT_STRING = "authContext";
	
//	private static final String ROLE_STRING = "ROLE_";
	
	private MenuRoleRepository menuRoleRepository;
	
	private MenuMapper menuMapper;
	
	private MenuRepository menuRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	private MenuService menuService;
	
	public AuthorityMenuService(MenuRoleRepository menuRoleRepository, MenuMapper menuMapper, MenuRepository menuRepository,
			RoleRepository roleRepository){
		this.menuRoleRepository = menuRoleRepository;
		this.menuMapper = menuMapper;
		this.menuRepository = menuRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public Map<String, Object> createContext() {
		Map<String, Object> processContext = new HashMap<>();
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication  = securityContext.getAuthentication();
		processContext.put(AUTH_CONTEXT_STRING, authentication);
		
		return processContext;
	}
	
	@Override
	public Boolean validateBeforeProces(Map<String, Object> processContext) {
		return processContext.get(AUTH_CONTEXT_STRING) != null;
	}

	@Override
	@Transactional(readOnly = true)
	public void process(Map<String, Object> processContext) {
		Authentication authentication  = (Authentication) processContext.get(AUTH_CONTEXT_STRING);
		List<MenuDTO> menuList = new ArrayList<>();
		
		for(GrantedAuthority auth : authentication.getAuthorities()) {
			getMenuList(menuList, auth);
		}
		
		processContext.put("menuPage", new PageImpl<MenuDTO>(menuList.stream().distinct().collect(Collectors.toList())));
	}

	@Override
	public Boolean validateAfterProcess(Map<String, Object> processContext) {
		return processContext.get(AUTH_CONTEXT_STRING) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Page<MenuDTO> getResponseEntity(Map<String, Object> processContext) {
		return (Page<MenuDTO>) processContext.get("menuPage");
	}
	
	private void getMenuList(List<MenuDTO> menuList, GrantedAuthority auth) {
		Optional<Role> optRole = roleRepository.findByRoleCodeAndIsActive(auth.getAuthority(), 1);
		if(optRole.isPresent()) {
			List<MenuRole> menuRoleList = menuRoleRepository.findByRoleName(auth.getAuthority());
			
			List<Long> menuIds = menuRoleList.stream()
					.map(menuRole->{return menuRole.getMenuId().longValue();})
					.collect(Collectors.toList());
			List<Menu> menus = menuRepository.findByMenuIdInAndParentIdIsNullAndIsDeletedFalseOrderByOrderAsc(menuIds);
						
			List<Menu> newMenuList = new ArrayList<Menu>();
			for (Menu menu : menus) {
				//depth 2
				List<Menu> newMenuList2 = new ArrayList<Menu>();
				for (Menu menu2 : menu.getMenus()) {
					if(!menu2.getIsDeleted()) {
						for (MenuRole role : menu2.getRoles()) {
							if(role.getAuthority().getName().equals(auth.getAuthority())) {
								//depth 3
								List<Menu> newMenuList3 = new ArrayList<Menu>();
								for (Menu menu3 : menu2.getMenus()) {
									if(!menu3.getIsDeleted()) {
										for (MenuRole role2 : menu3.getRoles()) {
											if(role2.getAuthority().getName().equals(auth.getAuthority())) {
												newMenuList3.add(menu3);
											}
										}
									}
								}
								menu2.setMenus(newMenuList3);
								newMenuList2.add(menu2);
							}
						}
					}
				}
				menu.setMenus(newMenuList2);
				newMenuList.add(menu);
			}
			
			menuList.addAll(menuMapper.toDto(newMenuList));
		}
	}
	
}

