package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.ProductImage;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.ProductRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IProductService;
import vn.iotstar.TheLaApp.service.IUserService;
import vn.iotstar.TheLaApp.util.ConvertToDto;
import vn.iotstar.TheLaApp.util.JsonEncryptor;
import vn.iotstar.TheLaApp.util.PasswordUtils;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ConvertToDto convert;
	
	public List<ProductDto> getAllActiveAndNotDeletedProducts() {
		List<Product> list = productRepository.getAllActiveAndNotDeletedProducts();
		return convert.convertToListProductDto(list);
	}
	
	public List<ProductDto> getActiveAndNotDeletedProductsByCategoryId(Long categoryId) {
		List<Product> list = productRepository.getActiveAndNotDeletedProductsByCategoryId(categoryId);
		return convert.convertToListProductDto(list);
	}
	
	public List<ProductDto> get10RecentActiveAndNotDeletedProducts() {
		List<Product> list = productRepository.get10RecentActiveAndNotDeletedProducts();
		return convert.convertToListProductDto(list);
	}
	
	public List<ProductDto> getTop10BestSellingActiveAndNotDeletedProducts() {
		List<Product> list = productRepository.getTop10BestSellingActiveAndNotDeletedProducts();
		return convert.convertToListProductDto(list);
	}

	@Override
	public List<ProductDto> getProductsBySearch(String word) {
		List<Product> list = productRepository.getProductsBySearch(word); 
		return convert.convertToListProductDto(list);
	}
}
