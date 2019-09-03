package id.co.indoeskrim.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import id.co.indoeskrim.service.MenuRoleService;
import id.co.indoeskrim.service.dto.MenuRoleDTO;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing MenuRole.
 */
@RestController
@RequestMapping("/api")
public class MenuRoleResource {

    private final Logger log = LoggerFactory.getLogger(MenuRoleResource.class);

    private static final String ENTITY_NAME = "menuRole";

    private MenuRoleService menuRoleService;

    public MenuRoleResource(MenuRoleService menuRoleService) {
        this.menuRoleService = menuRoleService;
    }

    /**
     * POST  /menu-roles : Create a new menuRole.
     *
     * @param menuRoleDTO the menuRoleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuRoleDTO, or with status 400 (Bad Request) if the menuRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-roles")
    public ResponseEntity<MenuRoleDTO> createMenuRole(@Valid @RequestBody MenuRoleDTO menuRoleDTO) throws URISyntaxException {
        log.debug("REST request to save MenuRole : {}", menuRoleDTO);
        if (menuRoleDTO.getMenuRoleId() != null) {
            throw new BadRequestAlertException("A new menuRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuRoleDTO result = menuRoleService.save(menuRoleDTO);
        return ResponseEntity.created(new URI("/api/menu-roles/" + result.getMenuRoleId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getMenuRoleId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-roles : Updates an existing menuRole.
     *
     * @param menuRoleDTO the menuRoleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuRoleDTO,
     * or with status 400 (Bad Request) if the menuRoleDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuRoleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-roles")
    public ResponseEntity<MenuRoleDTO> updateMenuRole(@Valid @RequestBody MenuRoleDTO menuRoleDTO) throws URISyntaxException {
        log.debug("REST request to update MenuRole : {}", menuRoleDTO);
        if (menuRoleDTO.getMenuRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuRoleDTO result = menuRoleService.save(menuRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuRoleDTO.getMenuRoleId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-roles : get all the menuRoles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuRoles in body
     */
    @GetMapping("/menu-roles")
    public ResponseEntity<List<MenuRoleDTO>> getAllMenuRoles(Pageable pageable) {
        log.debug("REST request to get a page of MenuRoles");
        Page<MenuRoleDTO> page = menuRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menu-roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /menu-roles/:id : get the "id" menuRole.
     *
     * @param id the id of the menuRoleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuRoleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menu-roles/{id}")
    public ResponseEntity<MenuRoleDTO> getMenuRole(@PathVariable Long id) {
        log.debug("REST request to get MenuRole : {}", id);
        Optional<MenuRoleDTO> menuRoleDTO = menuRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuRoleDTO);
    }

    /**
     * DELETE  /menu-roles/:id : delete the "id" menuRole.
     *
     * @param id the id of the menuRoleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-roles/{id}")
    public ResponseEntity<Void> deleteMenuRole(@PathVariable Long id) {
        log.debug("REST request to delete MenuRole : {}", id);
        menuRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
