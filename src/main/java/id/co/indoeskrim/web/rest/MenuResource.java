package id.co.indoeskrim.web.rest;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.indoeskrim.service.IServiceContainer;
import id.co.indoeskrim.service.MenuService;
import id.co.indoeskrim.service.dto.MenuDTO;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing Menu.
 */
@RestController
@RequestMapping("/api")
public class MenuResource {

    private final Logger log = LoggerFactory.getLogger(MenuResource.class);

    private static final String ENTITY_NAME = "menu";

    private MenuService menuService;
    
    private IServiceContainer<Page<MenuDTO>, Map<String, Object>>  authorityMenuService;

    public MenuResource(MenuService menuService, IServiceContainer<Page<MenuDTO>, Map<String, Object>> authorityMenuService) {
        this.menuService = menuService;
        this.authorityMenuService = authorityMenuService;
    }

    /**
     * POST  /menus : Create a new menu.
     *
     * @param menuDTO the menuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuDTO, or with status 400 (Bad Request) if the menu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menus")
    public ResponseEntity<MenuDTO> createMenu(@Valid @RequestBody MenuDTO menuDTO) throws URISyntaxException {
        log.debug("REST request to save Menu : {}", menuDTO);
        if (menuDTO.getMenuId() != null) {
            throw new BadRequestAlertException("A new menu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuDTO result = menuService.save(menuDTO);
        return null;
    }

    /**
     * PUT  /menus : Updates an existing menu.
     *
     * @param menuDTO the menuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuDTO,
     * or with status 400 (Bad Request) if the menuDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menus")
    public ResponseEntity<MenuDTO> updateMenu(@Valid @RequestBody MenuDTO menuDTO) throws URISyntaxException {
        log.debug("REST request to update Menu : {}", menuDTO);
        if (menuDTO.getMenuId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuDTO result = menuService.save(menuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuDTO.getMenuId().toString()))
            .body(result);
    }
    
    //update menu bulk
    @PutMapping("/bulkUpdate")
    public void bulkUpdate(@Valid @RequestBody List<MenuDTO> menuDTOs) throws URISyntaxException {
        log.debug("REST request to bulkUpdate Menu : {}", menuDTOs);
        menuService.bulkUpdate(menuDTOs);
    }

    /**
     * GET  /menus : get all the menus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menus in body
     */
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        log.debug("REST request to get a page of Menus");
        List<MenuDTO> list = menuService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
//    /**
//     * GET  /allmenus : get all the menus with Pagination.
//     * @param pageable
//     * @return
//     */
//    @GetMapping("/allmenus")
//    public ResponseEntity<Object> getAllMenusWithPagination(Pageable pageable) {
//		log.debug("REST request to get a page of Menu");
//		final Page<MenuDTO> page = menuService.findAll(pageable);
//		ResponseObj resp = new ResponseObj();
//		resp.setId("id-allmenus-" + UtilObject.getDate());
//		resp.setPage(page);
//		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/allmenus");
//		return new ResponseEntity<>(resp, headers, HttpStatus.OK);    	
//    }
    
    /**
     * GET  /allmenus : get all the menus with Pagination.
     * @param pageable
     * @return
     */
    @GetMapping("/allmenus")
    public ResponseEntity<List<MenuDTO>> getAllParentMenus() {
		log.debug("REST request to get a page of Menu");
		List<MenuDTO> list = menuService.findAllByParentIdNull();
		return new ResponseEntity<>(list, HttpStatus.OK);    	
    }

    /**
     * GET  /menus/:id : get the "id" menu.
     *
     * @param id the id of the menuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menus/{id}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable Long id) {
        log.debug("REST request to get Menu : {}", id);
        Optional<MenuDTO> menuDTO = menuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuDTO);
    }

    /**
     * DELETE  /menus/:id : delete the "id" menu.
     *
     * @param id the id of the menuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        log.debug("REST request to delete Menu : {}", id);
        menuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /menus : get all the menus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menus in body
     */
    @GetMapping("/menu-auth")
    @Timed
    public ResponseEntity<List<MenuDTO>> getAllMenuByAuth() {
        log.debug("REST request to get a page of Menus with Authentication");
        Page<MenuDTO> page = authorityMenuService.getResponse();
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/test-menu-auth");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
