package vn.iotstar.TheLaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.ProductImage;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	@Query(value = "SELECT * FROM product_images WHERE product_id = :productId", nativeQuery = true)
	List<ProductImage> getProductImagesByProductId(@Param("productId") Long productId);
}
