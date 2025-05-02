package vn.iotstar.TheLaApp.service;

import java.util.List;

import vn.iotstar.TheLaApp.dto.ProductImageDto;

public interface IProductImageService {
	List<ProductImageDto> getProductImagesByProductId(Long productId);
}
