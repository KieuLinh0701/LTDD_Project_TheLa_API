package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.CategoryDto;
import vn.iotstar.TheLaApp.dto.OrderDetailDto;
import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.dto.ReviewImageDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.entity.Category;
import vn.iotstar.TheLaApp.entity.Order;
import vn.iotstar.TheLaApp.entity.OrderDetail;
import vn.iotstar.TheLaApp.entity.Review;
import vn.iotstar.TheLaApp.entity.ReviewImage;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.CategoryRepository;
import vn.iotstar.TheLaApp.repository.OrderDetailRepository;
import vn.iotstar.TheLaApp.repository.OrderRepository;
import vn.iotstar.TheLaApp.repository.ReviewImageRepository;
import vn.iotstar.TheLaApp.repository.ReviewRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.ICategoryService;
import vn.iotstar.TheLaApp.service.IReviewService;
import vn.iotstar.TheLaApp.util.ConvertToDto;

@Service
public class ReviewService implements IReviewService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ReviewImageRepository imageRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private ConvertToDto convert;
	
	public List<ReviewDto> getReviewsByProductId(Long productId) {
		List<Review> list = repository.getReviewsByProductId(productId);
		return convert.convertToListReviewDto(list);
	}

	@Override
	public ResponseEntity<Map<String, Object>> newReview(OrderDto orderDto) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        List<OrderDetailDto> detailDtos = orderDto.getOrderDetails();

	        // Lấy thông tin đơn hàng và người dùng
	        Order order = orderRepository.getById(orderDto.getOrderId());
	        User user = order.getUser();

	        // Lặp qua các chi tiết đơn hàng và tạo đánh giá
	        for (OrderDetailDto item : detailDtos) {
	            OrderDetail orderDetail = orderDetailRepository.getById(item.getOrderDetailId());

	            // Tạo mới đối tượng Review
	            Review newReview = new Review(
	                    item.getReview().getRating(),
	                    item.getReview().getContent(),
	                    new Timestamp(System.currentTimeMillis()),
	                    user,
	                    orderDetail.getProductSize().getProduct(),
	                    false,
	                    orderDetail
	            );
	            newReview = repository.save(newReview);

	            // Xử lý ảnh đánh giá
	            List<ReviewImageDto> imageDtos = item.getReview().getReviewImages();
	            for (ReviewImageDto x : imageDtos) {
	                ReviewImage newImage = new ReviewImage(
	                        x.getImage(),
	                        newReview
	                );
	                imageRepository.save(newImage);
	            }
	        }

	        // Trả về phản hồi thành công
	        response.put("success", true);
	        response.put("message", "Đánh giá đã được lưu thành công.");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // Xử lý lỗi
	        response.put("success", false);
	        response.put("message", "Có lỗi xảy ra khi lưu đánh giá: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateReview(ReviewDto reviewDto) {
		 Map<String, Object> response = new HashMap<>();
		    try {
		        Review review = repository.getById(reviewDto.getReviewId());

		        // Cập nhật nội dung và đánh giá
		        review.setContent(reviewDto.getContent());
		        review.setRating(reviewDto.getRating());

		        // Danh sách hình ảnh hiện tại
		        List<ReviewImage> existingImages = review.getReviewImages();

		        // Danh sách hình ảnh từ reviewDto
		        List<ReviewImageDto> newImages = reviewDto.getReviewImages();

		        // Lấy danh sách ID các hình ảnh mới
		        List<Long> newImageIds = newImages.stream()
		            .map(ReviewImageDto::getImageId)
		            .filter(Objects::nonNull)
		            .collect(Collectors.toList());

		        // Xóa các hình ảnh không còn tồn tại trong danh sách mới
		        Iterator<ReviewImage> iterator = existingImages.iterator();
		        while (iterator.hasNext()) {
		            ReviewImage existingImage = iterator.next();
		            if (!newImageIds.contains(existingImage.getImageId())) {
		                iterator.remove(); // Xóa khỏi danh sách hiện tại
		                imageRepository.deleteById(existingImage.getImageId()); // Xóa khỏi cơ sở dữ liệu
		            }
		        }

		        // Cập nhật hoặc thêm hình ảnh mới
		        for (ReviewImageDto newImageDto : newImages) {
		            if (newImageDto.getImageId() != null) {
		                // Hình ảnh đã tồn tại, không cần thêm mới
		                continue;
		            }
		            // Tạo mới hình ảnh
		            ReviewImage newImage = new ReviewImage(newImageDto.getImage(), review);
		            newImage = imageRepository.save(newImage);
		            existingImages.add(newImage);
		        }

		        review.setIsModify(true);
		        // Lưu lại review cùng các thay đổi
		        repository.save(review);

	        response.put("message", "Review updated successfully");
	        response.put("status", "success");
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        response.put("message", "Error updating review: " + e.getMessage());
	        response.put("status", "error");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

}
