package id.co.indoeskrim.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
public class Role extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "description")
    private String description;

    @Column(name = "isActive")
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
		return "Role [roleCode=" + roleCode + ", description=" + description + ", isActive=" + isActive + "]";
	}

}
