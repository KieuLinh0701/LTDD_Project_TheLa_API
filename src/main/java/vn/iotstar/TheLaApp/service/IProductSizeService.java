package vn.iotstar.TheLaApp.service;

import java.util.List;

import vn.iotstar.TheLaApp.dto.ProductSizeDto;

public interface IProductSizeService {
	List<ProductSizeDto> getProductSizesByProductId(Long productId);
}
