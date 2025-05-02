package vn.iotstar.TheLaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query(value = "SELECT * FROM reviews WHERE product_id = :productId", nativeQuery = true)
	List<Review> getReviewsByProductId(@Param("productId") Long productId);
}
