package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.service.implement.ReviewService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

  @Autowired
  private ReviewService service;  
  
  @GetMapping
  public List<ReviewDto> getReviewsByProductId(@RequestParam("productId") Long productId) {
	  return service.getReviewsByProductId(productId);
  }
  
  @PostMapping("/new")
  public ResponseEntity<Map<String, Object>> newReview(@RequestBody OrderDto orderDto) {
	  return service.newReview(orderDto);
  }
  
  @PutMapping("/update")
  public ResponseEntity<Map<String, Object>> updateReview(@RequestBody ReviewDto reviewDto) {
	  return service.updateReview(reviewDto);
  }
}
