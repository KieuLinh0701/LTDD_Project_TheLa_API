package vn.iotstar.TheLaApp.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {
	private Long promotionId;
    private String name;
    private String image;
    private String description;
    private Double discountPercentage;
    private Double minimumOrderValue;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "GMT+7")
    private Timestamp startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "GMT+7")
    private Timestamp endDate;
    
    private Long quantity;
    private Long quantityUsed;
}
