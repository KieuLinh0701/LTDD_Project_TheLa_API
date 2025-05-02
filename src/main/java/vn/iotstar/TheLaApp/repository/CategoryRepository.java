package vn.iotstar.TheLaApp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.Category;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "SELECT * FROM categories WHERE is_active = 1 AND is_delete = 0", nativeQuery = true)
    List<Category> getAllActiveAndNotDeletedCategories();
}
