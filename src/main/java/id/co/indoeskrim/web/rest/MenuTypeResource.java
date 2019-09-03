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

import id.co.indoeskrim.service.MenuTypeService;
import id.co.indoeskrim.service.dto.MenuTypeDTO;
import id.co.indoeskrim.web.rest.errors.BadRequestAlertException;
import id.co.indoeskrim.web.rest.util.HeaderUtil;
import id.co.indoeskrim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing MenuType.
 */
@RestController
@RequestMapping("/api")
public class MenuTypeResource {

    private final Logger log = LoggerFactory.getLogger(MenuTypeResource.class);

    private static final String ENTITY_NAME = "menuType";

    private MenuTypeService menuTypeService;

    public MenuTypeResource(MenuTypeService menuTypeService) {
        this.menuTypeService = menuTypeService;
    }

    /**
     * POST  /menu-types : Create a new menuType.
     *
     * @param menuTypeDTO the menuTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuTypeDTO, or with status 400 (Bad Request) if the menuType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-types")
    public ResponseEntity<MenuTypeDTO> createMenuType(@Valid @RequestBody MenuTypeDTO menuTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MenuType : {}", menuTypeDTO);
        if (menuTypeDTO.getMenuTypeId() != null) {
            throw new BadRequestAlertException("A new menuType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuTypeDTO result = menuTypeService.save(menuTypeDTO);
        return ResponseEntity.created(new URI("/api/menu-types/" + result.getMenuTypeId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getMenuTypeId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-types : Updates an existing menuType.
     *
     * @param menuTypeDTO the menuTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuTypeDTO,
     * or with status 400 (Bad Request) if the menuTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the menuTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-types")
    public ResponseEntity<MenuTypeDTO> updateMenuType(@Valid @RequestBody MenuTypeDTO menuTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MenuType : {}", menuTypeDTO);
        if (menuTypeDTO.getMenuTypeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuTypeDTO result = menuTypeService.save(menuTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuTypeDTO.getMenuTypeId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-types : get all the menuTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuTypes in body
     */
    @GetMapping("/menu-types")
    public ResponseEntity<List<MenuTypeDTO>> getAllMenuTypes(Pageable pageable) {
        log.debug("REST request to get a page of MenuTypes");
        Page<MenuTypeDTO> page = menuTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menu-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /menu-types/:id : get the "id" menuType.
     *
     * @param id the id of the menuTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/menu-types/{id}")
    public ResponseEntity<MenuTypeDTO> getMenuType(@PathVariable Long id) {
        log.debug("REST request to get MenuType : {}", id);
        Optional<MenuTypeDTO> menuTypeDTO = menuTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuTypeDTO);
    }

    /**
     * DELETE  /menu-types/:id : delete the "id" menuType.
     *
     * @param id the id of the menuTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-types/{id}")
    public ResponseEntity<Void> deleteMenuType(@PathVariable Long id) {
        log.debug("REST request to delete MenuType : {}", id);
        menuTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
