package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.PaymentMethodDto;
import vn.iotstar.TheLaApp.dto.ProductSizeDto;
import vn.iotstar.TheLaApp.dto.ReviewDto;
import vn.iotstar.TheLaApp.service.implement.PaymentMethodService;
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
@RequestMapping("/payment-methods")
public class PaymentMethodController {

  @Autowired
  private PaymentMethodService service;  
  
  @GetMapping
  public List<PaymentMethodDto> PaymentMethod() {
	  return service.getAllPaymentMethods();
  }
}
