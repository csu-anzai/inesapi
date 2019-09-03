package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.Menu;
import id.co.indoeskrim.service.dto.MenuResponseDTO;

/**
 * Mapper for the entity Menu and its DTO MenuDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuTypeMapper.class})
public interface MenuResponseMapper extends EntityMapper<MenuResponseDTO, Menu> {

    default Menu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setMenuId(id);
        return menu;
    }
}
