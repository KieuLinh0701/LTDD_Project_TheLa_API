package vn.iotstar.TheLaApp.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Long cartItemId;
	private int quantity;
	private Timestamp addDate;
    private ProductSizeDto productSize;
}
