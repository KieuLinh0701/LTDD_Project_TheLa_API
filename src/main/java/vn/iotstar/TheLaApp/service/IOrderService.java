package vn.iotstar.TheLaApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import vn.iotstar.TheLaApp.dto.OrderDto;

public interface IOrderService {
	List<OrderDto> getOrders(Long userId, String status);
	List<OrderDto> getOrdersBySearch(Long userId, String word);
	ResponseEntity<?> newOrder(OrderDto dto, Long userId);
	ResponseEntity<?> updateOrder(@RequestBody OrderDto dto);
	ResponseEntity<OrderDto> getOrderById(Long orderId);
}
