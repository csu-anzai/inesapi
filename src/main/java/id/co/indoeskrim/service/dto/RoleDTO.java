package id.co.indoeskrim.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Warehouse entity.
 */
public class RoleDTO implements Serializable {

    private String roleCode;

    private String description;

    private Boolean isActive;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleCode=" + roleCode + ", description=" + description + ", isActive=" + isActive + "]";
	}
}
