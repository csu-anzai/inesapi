package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.Role;
import id.co.indoeskrim.service.dto.RoleDTO;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {


    default Role fromId(String id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleCode(id);
        return role;
    }
}
