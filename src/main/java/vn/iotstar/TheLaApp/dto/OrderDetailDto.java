package vn.iotstar.TheLaApp.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
	
	private Long orderDetailId;
    private Integer quantity;
    private BigDecimal price;
    private ProductSizeDto productSize;
    private ReviewDto review;
}
