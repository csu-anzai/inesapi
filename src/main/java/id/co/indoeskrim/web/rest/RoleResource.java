package id.co.indoeskrim.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.indoeskrim.service.RoleService;
import id.co.indoeskrim.service.dto.MenuResponseDTO;
import id.co.indoeskrim.service.dto.RoleDTO;
import id.co.indoeskrim.service.dto.RoleMenuRequestDTO;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Warehouse.
 */
@RestController
@RequestMapping("/api")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private static final String ENTITY_NAME = "role";

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to save Role : {}", roleDTO);
        
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.created(new URI("/api/role/" + result.getRoleCode()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getRoleCode().toString()))
            .body(result);
    }
    
    
    @PutMapping("/role")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to update Role : {}", roleDTO);
        
        RoleDTO result = roleService.update(roleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, roleDTO.getRoleCode().toString()))
            .body(result);
    }

    @GetMapping("/role/{roleCode}")
    public ResponseEntity<RoleDTO> getWarehouse(@PathVariable String roleCode) {
        log.debug("REST request to get Warehouse : {}", roleCode);
        Optional<RoleDTO> roleDTO = roleService.findByRoleCode(roleCode);
        return ResponseUtil.wrapOrNotFound(roleDTO);
    }
    
    
    @GetMapping("/role")
    public ResponseEntity<List<RoleDTO>> getAllRoles(Pageable pageable) {
        log.debug("REST request to get a page of Role");
        Page<RoleDTO> page = roleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/role");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @GetMapping("/all-menu")
    public List<MenuResponseDTO> getAllMenu(@RequestParam(required = false) Optional<String> roleCode) {
		log.debug("REST request to get Role Menu"); 
		List<MenuResponseDTO> list = roleService.menuList(roleCode);
		
		return list;    	
    }
    
    @PostMapping("/create-role-menu")
    public ResponseEntity<RoleMenuRequestDTO> createRoleMenu(@RequestBody RoleMenuRequestDTO roleMenuRequestDTO) throws URISyntaxException {
        log.debug("REST request to save Role : {}", roleMenuRequestDTO);
//        
        RoleMenuRequestDTO result = roleService.createRoleMenu(roleMenuRequestDTO);
        return ResponseEntity.created(new URI("/api/create-role-menu/" + result.getRoleCode()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getRoleCode().toString()))
            .body(result);
    }
}
