package id.co.indoeskrim.service.mapper;


import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.MenuType;
import id.co.indoeskrim.service.dto.MenuTypeDTO;

/**
 * Mapper for the entity MenuType and its DTO MenuTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MenuTypeMapper extends EntityMapper<MenuTypeDTO, MenuType> {



    default MenuType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuType menuType = new MenuType();
        menuType.setMenuTypeId(id);
        return menuType;
    }
}
