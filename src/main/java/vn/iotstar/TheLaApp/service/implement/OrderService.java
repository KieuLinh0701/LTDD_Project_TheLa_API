package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vn.iotstar.TheLaApp.dto.OrderDetailDto;
import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.entity.Order;
import vn.iotstar.TheLaApp.entity.OrderDetail;
import vn.iotstar.TheLaApp.entity.Promotion;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.OrderDetailRepository;
import vn.iotstar.TheLaApp.repository.OrderRepository;
import vn.iotstar.TheLaApp.repository.PaymentMethodRepository;
import vn.iotstar.TheLaApp.repository.ProductSizeRepository;
import vn.iotstar.TheLaApp.repository.PromotionRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IOrderService;
import vn.iotstar.TheLaApp.util.ConvertToDto;
import vn.iotstar.TheLaApp.util.PasswordUtils;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductSizeRepository productSizeRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private ConvertToDto convert;

	@Override
	public List<OrderDto> getOrders(Long userId, String status) {
		return convert.convertToListOrderDto(repository.getOrders(userId, status));
	}

	@Override
	public List<OrderDto> getOrdersBySearch(Long userId, String word) {
		return convert.convertToListOrderDto(repository.getOrdersBySearch(userId, word));
	}

	@Override
	public ResponseEntity<?> newOrder(OrderDto dto, Long userId) {
		
		Map<String, Object> response = new HashMap<>();
	    
		Promotion promotion = null;

		if (dto.getPromotion() != null && dto.getPromotion().getPromotionId() != null) {
		    promotion = promotionRepository.findById(dto.getPromotion().getPromotionId()).orElse(null);
		}

	    try {
	        Order newOrder = new Order(
	        		dto.getTotalPrice(),
	        		dto.getNote(),
	        		new Timestamp(System.currentTimeMillis()),
	        		dto.getDeliveryAddress(),
	        		dto.getDeliveryPhone(),
	        		dto.getStatus(),
	        		dto.getName(),
	        		promotion,
	        		userRepository.getById(userId),
	        		paymentMethodRepository.getById(dto.getPaymentMethod().getPaymentId())
	        		);
	        
	        newOrder = repository.save(newOrder);
	        createListOrderDetail(dto.getOrderDetails(), newOrder);
	        
	        if (promotion != null) {
	        	promotion.setQuantityUsed(promotion.getQuantityUsed()+1);
	        	updateQuantityUsedPromotion(promotion);
	        }

	        // Trả về phản hồi thành công
	        return ResponseEntity.ok(convert.convertToOrderDTO(newOrder));
	    } catch (Exception e) {
	        // Trả về lỗi chung trong trường hợp có ngoại lệ khác
	        response.put("message", "Có lỗi xảy ra trong quá trình tạo đơn hàng mới!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	private void updateQuantityUsedPromotion(Promotion promotion) {
		promotionRepository.save(promotion);
	}
	
	private void createListOrderDetail(List<OrderDetailDto> orderDetailDtos, Order newOrder) {
		for (OrderDetailDto item:orderDetailDtos) {
			createOrderDetail(item, newOrder);
		}
	}
	
	private void createOrderDetail(OrderDetailDto orderDetailDto, Order newOrder) {
		OrderDetail newOrderDetail = new OrderDetail(
				orderDetailDto.getQuantity(),
				orderDetailDto.getPrice(),
				productSizeRepository.getById(orderDetailDto.getProductSize().getProductSizeId()),
				newOrder
				
			);
		orderDetailRepository.save(newOrderDetail);	
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateOrder(OrderDto dto) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        String status = dto.getStatus();
	        Timestamp deliveryDate = dto.getDeliveryDate();
	        
	        // Lấy đơn hàng từ cơ sở dữ liệu
	        Order order = repository.getById(dto.getOrderId());
	        
	        // Cập nhật thông tin đơn hàng
	        order.setDeliveryDate(deliveryDate);
	        order.setStatus(status);
	        
	        // Lưu đơn hàng đã cập nhật vào cơ sở dữ liệu
	        repository.save(order);
	        
	        Promotion promotion = order.getPromotion();
	        if (promotion != null) {
	        	promotion.setQuantityUsed(promotion.getQuantityUsed()-1);
	        	updateQuantityUsedPromotion(promotion);
	        }
	        
	        // Trả về phản hồi thành công
	        response.put("message", "Cập nhật đơn hàng thành công");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Trả về phản hồi lỗi nếu có ngoại lệ
	        response.put("message", "Có lỗi xảy ra khi cập nhật đơn hàng");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<OrderDto> getOrderById(Long orderId) {
		try {
	          // Gọi service để lấy thông tin đơn hàng
	          Order order = repository.getById(orderId);

	          // Trả về thông tin đơn hàng cùng mã 200
	          return ResponseEntity.ok(convert.convertToOrderDTO(order));
	      } catch (Exception e) {
	          // Xử lý lỗi, trả về mã 500
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                  .body(null);
	      }
	}

}
