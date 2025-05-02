package vn.iotstar.TheLaApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "SELECT * FROM orders WHERE status = :status AND user_id = :userId ORDER BY create_date DESC", nativeQuery = true)
    List<Order> getOrders(@Param("userId") Long userId, @Param("status") String status);
	
	@Query(value = "SELECT DISTINCT o.*\n"
	        + "FROM orders o\n"
	        + "JOIN order_details od ON o.order_id = od.order_id\n"
	        + "JOIN products p ON p.product_id = (SELECT product_id FROM product_sizes WHERE product_size_id = od.product_size_id)\n"
	        + "JOIN product_sizes ps ON od.product_size_id = ps.product_size_id\n"
	        + "WHERE p.name LIKE CONCAT('%', :word, '%') AND o.user_id = :userId\n"
	        + "ORDER BY o.create_date DESC;", nativeQuery = true)
	List<Order> getOrdersBySearch(@Param("userId") Long userId, @Param("word") String word);
}
