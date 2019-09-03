package id.co.indoeskrim.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A MenuRole.
 */
@Entity
@Table(name = "menu_role")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuRole extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_role_seq")
    @SequenceGenerator(name = "menu_role_seq", sequenceName = "menu_role_seq", allocationSize = 1)
    @Column(name = "menu_role_id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @NotNull
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;
    
    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "name", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Authority authority;
    
//    @ManyToOne
//    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id", nullable = true, insertable = false, updatable = false)
//    @JsonIgnore
//    private Menu menu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getMenuRoleId() {
        return id;
    }

    public void setMenuRoleId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public MenuRole roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public MenuRole menuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    
    public Authority getAuthority() {
		return authority;
	}
    
    public MenuRole authority(Authority authority) {
		this.authority = authority;
		return this;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

//	public Menu getMenu() {
//		return menu;
//	}
	
//	public MenuRole authority(Menu menu) {
//		this.menu = menu;
//		return this;
//	}

//	public void setMenu(Menu menu) {
//		this.menu = menu;
//	}
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuRole menuRole = (MenuRole) o;
        if (menuRole.getMenuRoleId() == null || getMenuRoleId() == null) {
            return false;
        }
        return Objects.equals(getMenuRoleId(), menuRole.getMenuRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuRoleId());
    }

    @Override
    public String toString() {
        return "MenuRole{" +
            "id=" + getMenuRoleId() +
            ", roleName='" + getRoleName() + "'" +
            ", menuId=" + getMenuId() +
            "}";
    }
}
