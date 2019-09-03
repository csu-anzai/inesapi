package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.MenuRole;
import id.co.indoeskrim.service.dto.MenuRoleDTO;

/**
 * Mapper for the entity MenuRole and its DTO MenuRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface MenuRoleMapper extends EntityMapper<MenuRoleDTO, MenuRole> {

    default MenuRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuRole menuRole = new MenuRole();
        menuRole.setMenuRoleId(id);
        return menuRole;
    }
}
