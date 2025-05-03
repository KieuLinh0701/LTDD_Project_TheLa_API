package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.OrderDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.service.implement.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService; 
  
  @GetMapping
  public List<ProductDto> getAllActiveAndNotDeletedProducts() {
	  return productService.getAllActiveAndNotDeletedProducts();
  }
  
  @GetMapping("/by-category")
  public List<ProductDto> getActiveAndNotDeletedProductsByCategoryId(@RequestParam("categoryId") Long categoryId) {
	  return productService.getActiveAndNotDeletedProductsByCategoryId(categoryId);
  }
  
  @GetMapping("/best-seller")
  public List<ProductDto> getTop10BestSellingActiveAndNotDeletedProducts() {
	  return productService.getTop10BestSellingActiveAndNotDeletedProducts();
  }
  
  @GetMapping("/latest")
  public List<ProductDto> get10RecentActiveAndNotDeletedProducts() {
	  return productService.get10RecentActiveAndNotDeletedProducts();
  }
  
  @GetMapping("/search")
  public List<ProductDto> getProductsBySearch(@RequestParam("word") String word) {
	  return productService.getProductsBySearch(word);
  }
}
