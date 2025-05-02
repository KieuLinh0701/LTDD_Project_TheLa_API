package vn.iotstar.TheLaApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;

public interface IReviewService {
	List<ReviewDto> getReviewsByProductId(Long productId);
	ResponseEntity<Map<String, Object>> newReview(OrderDto orderDto);
	ResponseEntity<Map<String, Object>> updateReview(ReviewDto reviewDto);
}
