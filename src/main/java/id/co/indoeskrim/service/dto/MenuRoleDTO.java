package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import id.co.indoeskrim.domain.Authority;
import id.co.indoeskrim.domain.Menu;

/**
 * A DTO for the MenuRole entity.
 */
public class MenuRoleDTO implements Serializable {

    private Long menuRoleId;

    @NotNull
    @Size(max = 50)
    private String roleName;

    @NotNull
    private Integer menuId;
    
    private Menu menu;
    
    private Authority authority;

    public Long getMenuRoleId() {
        return menuRoleId;
    }

    public void setMenuRoleId(Long menuRoleId) {
        this.menuRoleId = menuRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    
    public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuRoleDTO menuRoleDTO = (MenuRoleDTO) o;
        if (menuRoleDTO.getMenuRoleId() == null || getMenuRoleId() == null) {
            return false;
        }
        return Objects.equals(getMenuRoleId(), menuRoleDTO.getMenuRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuRoleId());
    }

    @Override
    public String toString() {
        return "MenuRoleDTO{" +
            "menuRoleId=" + getMenuRoleId() +
            ", roleName='" + getRoleName() + "'" +
            ", menuId=" + getMenuId() +
            "}";
    }
}
