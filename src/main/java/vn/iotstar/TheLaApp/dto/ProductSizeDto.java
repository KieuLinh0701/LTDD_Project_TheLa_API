package vn.iotstar.TheLaApp.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeDto {
	private Long productSizeId;
	private BigDecimal price;
    private SizeDto size;
    private ProductDto product;
}