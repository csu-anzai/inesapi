package id.co.indoeskrim.service.dto;


import java.io.Serializable;

/**
 * A DTO for the Category entity.
 */
public class CategoryDTO implements Serializable {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
    private Boolean isActive;
    
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

    
}
