package id.co.indoeskrim.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Menu extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", allocationSize = 1)
    @Column(name = "menu_id")
    private Long menuId;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Size(max = 512)
    @Column(name = "page_uri", length = 512)
    private String pageUri;

    @NotNull
    @Column(name = "menu_type_id", nullable = false)
    private Integer menuTypeId;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "parent_id", nullable = false)
    private Integer parentId;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", referencedColumnName = "menu_id", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Menu parent;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    @OrderBy("order asc")  
    private List<Menu> menus;
    
    @Column(name = "\"order\"")
    private Integer order;
    
    @Size(max = 512)
    @Column(name = "image", length = 512)
    private String image;
    
    @OneToMany(mappedBy = "menuId", targetEntity=MenuRole.class)
    private Set<MenuRole> roles;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public Menu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Menu description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageUri() {
        return pageUri;
    }

    public Menu pageUri(String pageUri) {
        this.pageUri = pageUri;
        return this;
    }

    public void setPageUri(String pageUri) {
        this.pageUri = pageUri;
    }

    public Integer getMenuTypeId() {
        return menuTypeId;
    }

    public Menu menuTypeId(Integer menuTypeId) {
        this.menuTypeId = menuTypeId;
        return this;
    }

    public void setMenuTypeId(Integer menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Set<MenuRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MenuRole> roles) {
		this.roles = roles;
	}
	
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        if (menu.getMenuId() == null || getMenuId() == null) {
            return false;
        }
        return Objects.equals(getMenuId(), menu.getMenuId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuId());
    }

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", name=" + name + ", description=" + description + ", pageUri=" + pageUri
				+ ", menuTypeId=" + menuTypeId + ", isDeleted=" + isDeleted + ", parentId=" + parentId + ", parent="
				+ parent + ", menus=" + menus + ", order=" + order + ", image=" + image + ", roles=" + roles + "]";
	}

    
}
