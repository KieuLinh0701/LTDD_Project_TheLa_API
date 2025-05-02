package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.ProductImageDto;
import vn.iotstar.TheLaApp.dto.ProductSizeDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.service.implement.ProductImageService;
import vn.iotstar.TheLaApp.service.implement.ProductSizeService;
import vn.iotstar.TheLaApp.service.implement.ReviewService;

import java.util.List;

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
@RequestMapping("/product-images")
public class ProductImageController {

  @Autowired
  private ProductImageService service;  
  
  @GetMapping
  public List<ProductImageDto> getProductImagesByProductId(@RequestParam("productId") Long productId) {
	  return service.getProductImagesByProductId(productId);
  }
}
