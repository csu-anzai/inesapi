package id.co.indoeskrim.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A MenuType.
 */
@Entity
@Table(name = "menu_type")
public class MenuType extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_type_seq")
    @SequenceGenerator(name = "menu_type_seq", sequenceName = "menu_type_seq", allocationSize = 1)
    @Column(name = "menu_type_id")
    private Long id;

    @NotNull
    @Size(max = 64)
    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getMenuTypeId() {
        return id;
    }

    public void setMenuTypeId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MenuType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MenuType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        MenuType menuType = (MenuType) o;
        if (menuType.getMenuTypeId() == null || getMenuTypeId() == null) {
            return false;
        }
        return Objects.equals(getMenuTypeId(), menuType.getMenuTypeId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMenuTypeId());
    }

    @Override
    public String toString() {
        return "MenuType{" +
            "id=" + getMenuTypeId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
