package vn.iotstar.TheLaApp.dto;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.iotstar.TheLaApp.entity.ProductImage;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private Boolean status;
    private String productMainImage;
}
