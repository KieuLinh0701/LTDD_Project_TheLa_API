package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.ProductDto;
import vn.iotstar.TheLaApp.dto.PromotionDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.Product;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.implement.ProductService;
import vn.iotstar.TheLaApp.service.implement.PromotionService;
import vn.iotstar.TheLaApp.service.implement.SendMailService;
import vn.iotstar.TheLaApp.service.implement.UserService;
import vn.iotstar.TheLaApp.util.JsonEncryptor;
import vn.iotstar.TheLaApp.util.PasswordUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
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
@RequestMapping("/promotions")
public class PromotionController {

  @Autowired
  private PromotionService service;  
  
  @GetMapping
  public List<PromotionDto> getAllActiveAndNotDeletedPromotions() {
	  return service.getAllActiveAndNotDeletedPromotions();
  }
  
  @GetMapping("/for-order")
  public List<PromotionDto> getPromotionsForOrder(@RequestParam("total") Double total) {
	  return service.getPromotionsForOrder(total);
  }
}