package vn.iotstar.TheLaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.CartItem;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query(value = "SELECT * FROM cart_items WHERE cart_id = :cartId ORDER BY add_date DESC;", nativeQuery = true)
	List<CartItem> getCartItemsByCartId(@Param("cartId") Long cartId);
	
	@Query(value = "SELECT TOP 1 * FROM cart_items WHERE cart_id = :cartId AND product_size_id = :productSizeId;", nativeQuery = true)
	CartItem findCartItemsByCartAndIdProductSizeId(@Param("cartId") Long cartId, @Param("productSizeId") Long productSizeId);
}
