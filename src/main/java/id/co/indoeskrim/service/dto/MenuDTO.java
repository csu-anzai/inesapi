package id.co.indoeskrim.service.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import id.co.indoeskrim.domain.Menu;


/**
 * A DTO for the Menu entity.
 */
public class MenuDTO implements Serializable {

    private Long menuId;

    @NotNull
    @Size(max = 50)
    private String name;

    private String description;

    @Size(max = 512)
    private String pageUri;

    @NotNull
    private Integer menuTypeId;
    
    private Boolean isDeleted = false;
    
    private Integer parentId;
    
    private List<Menu> menus;
    
    private Integer order;
    
    private String image;
    
    private List<String> authorities;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long id) {
        this.menuId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageUri() {
        return pageUri;
    }

    public void setPageUri(String pageUri) {
        this.pageUri = pageUri;
    }

    public Integer getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(Integer menuType) {
        this.menuTypeId = menuType;
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
	
	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if (menuDTO.getMenuId() == null || getMenuId() == null) {
            return false;
        }
        return Objects.equals(getMenuId(), menuDTO.getMenuId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuId());
    }

	@Override
	public String toString() {
		return "MenuDTO [menuId=" + menuId + ", name=" + name + ", description=" + description + ", pageUri=" + pageUri
				+ ", menuTypeId=" + menuTypeId + ", isDeleted=" + isDeleted + ", parentId=" + parentId + ", menus="
				+ menus + ", order=" + order + ", image=" + image + ", authorities=" + authorities + "]";
	}
}
