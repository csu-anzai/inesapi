package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.List;


public class RoleMenuRequestDTO implements Serializable {

    private String roleCode;

	private List<MenuRequestDTO> listMenu;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<MenuRequestDTO> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<MenuRequestDTO> listMenu) {
		this.listMenu = listMenu;
	}
	
    
}
