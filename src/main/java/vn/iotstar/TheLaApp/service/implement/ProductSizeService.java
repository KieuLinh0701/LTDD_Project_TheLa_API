package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.CategoryDto;
import vn.iotstar.TheLaApp.dto.ProductSizeDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.dto.ReviewImageDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.entity.Category;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.Review;
import vn.iotstar.TheLaApp.entity.ReviewImage;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.CategoryRepository;
import vn.iotstar.TheLaApp.repository.ProductSizeRepository;
import vn.iotstar.TheLaApp.repository.ReviewRepository;
import vn.iotstar.TheLaApp.service.ICategoryService;
import vn.iotstar.TheLaApp.service.IProductSizeService;
import vn.iotstar.TheLaApp.service.IReviewService;
import vn.iotstar.TheLaApp.util.ConvertToDto;

@Service
public class ProductSizeService implements IProductSizeService{
	
	@Autowired
	private ProductSizeRepository repository;
	
	@Autowired
	private ConvertToDto convert;
	
	public List<ProductSizeDto> getProductSizesByProductId(Long productId) {
		List<ProductSize> list = repository.getProductSizesByProductId(productId);
		return convert.convertToListProductSizeDto(list);
	}
}
