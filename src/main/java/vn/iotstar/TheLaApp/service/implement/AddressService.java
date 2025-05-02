package vn.iotstar.TheLaApp.service.implement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.entity.Address;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.AddressRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IAddressService;
import vn.iotstar.TheLaApp.util.ConvertToDto;

@Service
public class AddressService implements IAddressService{
	
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConvertToDto convert;

	@Override
	public ResponseEntity<?> newAddress(AddressDto dto, Long userId) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // Kiểm tra userId có tồn tại không
	        if (!userRepository.existsById(userId)) {
	            response.put("message", "Người dùng không tồn tại!");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        // Lấy thông tin người dùng từ cơ sở dữ liệu
	        User user = userRepository.getById(userId);

	        // Tạo đối tượng Address từ DTO
	        Address address = new Address(
	            dto.getCity(),
	            dto.getDistrict(),
	            dto.getCommune(),
	            dto.getDetail(),
	            user
	        );

	        // Lưu Address vào cơ sở dữ liệu
	        Address newAddress = repository.save(address);

	        return ResponseEntity.ok(convert.convertToAddressDto(newAddress));

	    } catch (Exception e) {
	        // Trả về phản hồi lỗi trong trường hợp ngoại lệ
	        response.put("message", "Có lỗi xảy ra trong quá trình tạo địa chỉ mới!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateAddress(AddressDto dto) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // Kiểm tra xem Address có tồn tại không
	        Long addressId = dto.getAddressId();
	        if (!repository.existsById(addressId)) {
	            response.put("message", "Địa chỉ không tồn tại!");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        // Lấy Address từ cơ sở dữ liệu
	        Address address = repository.getById(addressId);

	        // Cập nhật thông tin
	        address.setCity(dto.getCity());
	        address.setDistrict(dto.getDistrict());
	        address.setCommune(dto.getCommune());
	        address.setDetail(dto.getDetail());

	        // Lưu lại Address đã cập nhật
	        Address updatedAddress = repository.save(address);

	        // Trả về phản hồi thành công
	        response.put("message", "Địa chỉ đã được cập nhật thành công!");
	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        // Trả về phản hồi lỗi trong trường hợp xảy ra ngoại lệ
	        response.put("message", "Có lỗi xảy ra trong quá trình cập nhật địa chỉ!");
	        System.out.println(e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
}
