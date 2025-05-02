package vn.iotstar.TheLaApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.CategoryDto;
import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.Product;

public interface ICategoryService {
	List<CategoryDto> getAllActiveAndNotDeletedCategories();
}
