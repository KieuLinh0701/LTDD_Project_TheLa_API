package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.service.implement.OrderService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService service;  
  
  @GetMapping
  public List<OrderDto> getOrders(@RequestParam("userId") Long userId, @RequestParam("status") String status) {
	  return service.getOrders(userId, status);
  }
  
  @GetMapping("/search")
  public List<OrderDto> getOrdersBySearch(@RequestParam("userId") Long userId, @RequestParam("word") String word) {
	  return service.getOrdersBySearch(userId, word);
  }
  
  @PostMapping("/new")
  public ResponseEntity<?> newOrder(@RequestBody OrderDto dto, @RequestParam Long userId) {
  	return service.newOrder(dto, userId);
  }
  
  @PutMapping("/update")
  public ResponseEntity<?> updateOrder(@RequestBody OrderDto dto) {
  	return service.updateOrder(dto);
  }
  
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
      return service.getOrderById(orderId);
  }
}
