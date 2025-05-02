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

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.dto.PromotionDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.ProductImage;
import vn.iotstar.TheLaApp.entity.Promotion;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.ProductRepository;
import vn.iotstar.TheLaApp.repository.PromotionRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IProductService;
import vn.iotstar.TheLaApp.service.IPromotionService;
import vn.iotstar.TheLaApp.service.IUserService;
import vn.iotstar.TheLaApp.util.ConvertToDto;
import vn.iotstar.TheLaApp.util.JsonEncryptor;
import vn.iotstar.TheLaApp.util.PasswordUtils;

@Service
public class PromotionService implements IPromotionService{
	
	@Autowired
	private PromotionRepository repository;
	
	@Autowired
	private ConvertToDto convert;
	
	public List<PromotionDto> getAllActiveAndNotDeletedPromotions() {
		List<Promotion> list = repository.getAllActiveAndNotDeletedPromotions();
		return convert.convertToListPromotionDto(list);
	}

	@Override
	public List<PromotionDto> getPromotionsForOrder(Double total) {
		List<Promotion> list = repository.getPromotionsForOrder(total);
		return convert.convertToListPromotionDto(list);
	}
}
