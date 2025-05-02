package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.service.implement.AddressService;

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
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService service;  

    // Tạo địa chỉ mới
    @PostMapping
    public ResponseEntity<?> newAddress(@RequestBody AddressDto dto, @RequestParam Long userId) {
        return service.newAddress(dto, userId);
    }

    // Cập nhật địa chỉ
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateAddress(@RequestBody AddressDto dto) {
    	return service.updateAddress(dto);
    }
}

