package vn.iotstar.TheLaApp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT * FROM products WHERE is_active = 1 AND is_delete = 0", nativeQuery = true)
    List<Product> getAllActiveAndNotDeletedProducts();
	
	@Query(value = "SELECT * FROM products WHERE is_active = 1 AND is_delete = 0 AND category_id = :categoryId", nativeQuery = true)
    List<Product> getActiveAndNotDeletedProductsByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query(value = "SELECT TOP 10 p.*\n"
			+ "FROM products p\n"
			+ "INNER JOIN product_sizes ps ON p.product_id = ps.product_id\n"
			+ "INNER JOIN order_details od ON ps.product_size_id = od.product_size_id\n"
			+ "GROUP BY p.category_id, p.create_date, p.description, p.is_active, p.is_delete, p.name, p.product_id, p.status\n"
			+ "ORDER BY SUM(od.quantity) DESC", nativeQuery = true)
	List<Product> getTop10BestSellingActiveAndNotDeletedProducts();
	
	@Query(value = "SELECT TOP 10 * FROM products \n"
			+ "WHERE is_active = 1 AND is_delete = 0 \n"
			+ "ORDER BY create_date DESC", nativeQuery = true)
	List<Product> get10RecentActiveAndNotDeletedProducts(); 
}
