package id.co.indoeskrim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.indoeskrim.domain.Menu;


/**
 * Spring Data  repository for the Menu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findAllByParentIdNullOrderByOrderAsc();
	List<Menu> findByMenuIdInAndParentIdIsNullAndIsDeletedFalseOrderByOrderAsc(List<Long> menu_id);
	List<Menu> findByMenuIdInAndIsDeletedFalseOrderByOrderAsc(List<Long> menu_id);
	List<Menu> findByParentIdIsNullAndIsDeletedFalseOrderByOrderAsc();
	
	@Modifying
	@Query(value = "UPDATE Menu SET "
			+ "parent_id = case when :#{#menu.parentId} = 0 then null else :#{#menu.parentId} end, "
			+ "\"order\" = :#{#menu.order} "
			+ "where menu_id = :#{#menu.menuId}",
			nativeQuery = true)
	public int updateParentAndOrder(@Param("menu") Menu menu);
}
