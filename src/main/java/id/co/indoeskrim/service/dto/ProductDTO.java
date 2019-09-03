package id.co.indoeskrim.service.dto;
import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Lob;

/**
 * A DTO for the Product entity.
 */
public class ProductDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5216471900498860788L;

    private String productCode;

    private String productName;

    private String productDescription;

    private Long price;
    
    private Long quantity;
    
    private Boolean isActive;
    
    @Lob
    private byte[] image;
   
    private Integer minimumQuantity;
    
    private CategoryDTO category;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductDTO [productCode=" + productCode + ", productName=" + productName + ", productDescription="
				+ productDescription + ", price=" + price + ", quantity=" + quantity + ", isActive=" + isActive
				+ ", image=" + Arrays.toString(image) + ", minimumQuantity=" + minimumQuantity + ", category="
				+ category + "]";
	}
}
