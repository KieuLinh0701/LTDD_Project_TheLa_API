package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.dto.CartItemDto;
import vn.iotstar.TheLaApp.service.implement.AddressService;
import vn.iotstar.TheLaApp.service.implement.CartItemService;

import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService service;  

    // Tạo địa chỉ mới
    @PostMapping
    public ResponseEntity<?> newCartItem(@RequestBody CartItemDto dto, @RequestParam Long cartId) {
        return service.newCartItem(dto, cartId);
    }
    
    @PutMapping
    public ResponseEntity<?> updateCartItem(@RequestBody CartItemDto dto) {
        return service.updateCartItem(dto);
    }
    
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        return service.deleteCartItem(cartItemId);
    }
}

