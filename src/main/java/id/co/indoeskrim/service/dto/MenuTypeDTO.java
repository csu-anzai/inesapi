package id.co.indoeskrim.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the MenuType entity.
 */
public class MenuTypeDTO implements Serializable {

    private Long menuTypeId;

    @NotNull
    @Size(max = 64)
    private String name;

    private String description;

    public Long getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(Long id) {
        this.menuTypeId = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuTypeDTO menuTypeDTO = (MenuTypeDTO) o;
        if (menuTypeDTO.getMenuTypeId() == null || getMenuTypeId() == null) {
            return false;
        }
        return Objects.equals(getMenuTypeId(), menuTypeDTO.getMenuTypeId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuTypeId());
    }

    @Override
    public String toString() {
        return "MenuTypeDTO{" +
            "menuTypeId=" + getMenuTypeId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
