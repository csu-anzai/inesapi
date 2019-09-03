package id.co.indoeskrim.service.mapper;

import org.mapstruct.Mapper;

import id.co.indoeskrim.domain.Category;
import id.co.indoeskrim.service.dto.CategoryDTO;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {



    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(id);
        return category;
    }
}
