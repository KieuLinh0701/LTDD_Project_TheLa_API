package vn.iotstar.TheLaApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	@Query(value = "SELECT * FROM order_details WHERE order_id = :orderId", nativeQuery = true)
    List<OrderDetail> getOrderDetails(@Param("orderId") Long orderId);
}
