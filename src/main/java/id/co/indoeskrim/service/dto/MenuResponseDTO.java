package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * A DTO for the Menu entity.
 */
public class MenuResponseDTO implements Serializable {

    private Long menuId;

    @NotNull
    @Size(max = 50)
    private String name;

    private String description;

    @Size(max = 512)
    private String pageUri;

    @NotNull
    private Integer menuTypeId;
    
    private Boolean deleted = false;
    
    private Integer parentId;
    
    private List<MenuResponseDTO> menus;
    
    private Integer order;
    
    private String image;
    
    private Integer isRegister;

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

	public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public List<MenuResponseDTO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuResponseDTO> menus) {
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

	public Integer getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuResponseDTO menuDTO = (MenuResponseDTO) o;
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
		return "MenuResponseDTO [menuId=" + menuId + ", name=" + name + ", description=" + description + ", pageUri="
				+ pageUri + ", menuTypeId=" + menuTypeId + ", deleted=" + deleted + ", parentId=" + parentId
				+ ", menus=" + menus + ", order=" + order + ", image=" + image + ", isRegister=" + isRegister + "]";
	}  
}
