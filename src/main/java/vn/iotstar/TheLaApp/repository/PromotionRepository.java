package vn.iotstar.TheLaApp.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.Promotion;
import vn.iotstar.TheLaApp.entity.User;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	@Query(value = "SELECT * FROM promotions WHERE is_active = 1 AND is_delete = 0", nativeQuery = true)
    List<Promotion> getAllActiveAndNotDeletedPromotions();
	
	@Query(value = "SELECT * FROM promotions\n"
			+ "WHERE [is_active] = 1 \n"
			+ "AND [is_delete] = 0 \n"
			+ "AND [quantity] > [quantity_used] \n"
			+ "AND [start_date] <= GETDATE() \n"
			+ "AND [end_date] >= GETDATE() \n"
			+ "AND [minimum_order_value] <= :total", nativeQuery = true)
	List<Promotion> getPromotionsForOrder(@Param("total") Double total);
}
