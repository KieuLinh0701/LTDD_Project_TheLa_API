package vn.iotstar.TheLaApp.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.iotstar.TheLaApp.entity.Address;
import vn.iotstar.TheLaApp.entity.OrderDetail;
import vn.iotstar.TheLaApp.entity.Promotion;
import vn.iotstar.TheLaApp.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	private Long orderId;
    private BigDecimal totalPrice;
    private String note;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "GMT+7")
    private Timestamp createDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "GMT+7")
    private Timestamp deliveryDate;
    
    private String deliveryAddress;
    private String deliveryPhone;
    private String status;
    private PromotionDto promotion;
    private List<OrderDetailDto> orderDetails;
    private String name;
    private PaymentMethodDto paymentMethod;
}
