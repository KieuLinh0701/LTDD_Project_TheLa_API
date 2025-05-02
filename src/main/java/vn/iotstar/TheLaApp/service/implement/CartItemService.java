package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import vn.iotstar.TheLaApp.dto.AddressDto;
import vn.iotstar.TheLaApp.dto.CartItemDto;
import vn.iotstar.TheLaApp.entity.Address;
import vn.iotstar.TheLaApp.entity.Cart;
import vn.iotstar.TheLaApp.entity.CartItem;
import vn.iotstar.TheLaApp.entity.ProductSize;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.AddressRepository;
import vn.iotstar.TheLaApp.repository.CartItemRepository;
import vn.iotstar.TheLaApp.repository.CartRepository;
import vn.iotstar.TheLaApp.repository.ProductSizeRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IAddressService;
import vn.iotstar.TheLaApp.service.ICartItemService;
import vn.iotstar.TheLaApp.util.ConvertToDto;

@Service
public class CartItemService implements ICartItemService {
	
	@Autowired
	private CartItemRepository repository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductSizeRepository productSizeRepository;
	
	@Autowired
	private ConvertToDto convert;

	@Override
	public ResponseEntity<?> newCartItem(CartItemDto dto, Long cartId) {
		Map<String, Object> response = new HashMap<>();

	    try {
	        
	        if (!cartRepository.existsById(cartId)) {
	        	response.put("message", "Giỏ hàng không tồn tại!");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	        
	        Cart cart = cartRepository.getById(cartId);
	        
	        ProductSize productSize = productSizeRepository.getById(dto.getProductSize().getProductSizeId());
	        
	        CartItem cartItemExist = repository.findCartItemsByCartAndIdProductSizeId(cartId, productSize.getProductSizeId());
	        
	        CartItem newCartItem;
	        if (cartItemExist == null) {
	        	// Tạo đối tượng Address từ DTO
	        	CartItem cartItem = new CartItem (
		            dto.getQuantity(),
		            cart,
		            productSize,
		            new Timestamp(System.currentTimeMillis())
		        );

		        // Lưu Address vào cơ sở dữ liệu
		        newCartItem = repository.save(cartItem);
	        } else {
	        	cartItemExist.setQuantity(cartItemExist.getQuantity() + dto.getQuantity());
	        	cartItemExist.setAddDate(new Timestamp(System.currentTimeMillis()));
			    newCartItem = repository.save(cartItemExist);
	        }
	        
	        return ResponseEntity.ok(convert.convertToListCartItemDto(newCartItem.getCart().getCartItems()));

	    } catch (Exception e) {
	        // Trả về phản hồi lỗi trong trường hợp ngoại lệ
	        response.put("message", "Có lỗi xảy ra trong quá trình thêm sản phẩm vào giỏ hàng!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<?> updateCartItem(CartItemDto dto) {
		Map<String, Object> response = new HashMap<>();

	    try {
	        if (!repository.existsById(dto.getCartItemId())) {
	        	response.put("message", "Giỏ hàng chi tiết không tồn tại!");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	        
	        CartItem cartItem = repository.getById(dto.getCartItemId());
	        
	        cartItem.setQuantity(dto.getQuantity());
        	cartItem.setAddDate(new Timestamp(System.currentTimeMillis()));

	        // Lưu Address vào cơ sở dữ liệu
	        repository.save(cartItem);
	        
	        return ResponseEntity.ok(convert.convertToListCartItemDto(cartItem.getCart().getCartItems()));

	    } catch (Exception e) {
	        // Trả về phản hồi lỗi trong trường hợp ngoại lệ
	        response.put("message", "Có lỗi xảy ra trong quá trình thêm sản phẩm vào giỏ hàng!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	@Override
	public ResponseEntity<?> deleteCartItem(Long cartItemId) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        // Kiểm tra xem CartItem có tồn tại không
	        if (!repository.existsById(cartItemId)) {
	            response.put("message", "Giỏ hàng chi tiết không tồn tại!");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        // Lấy CartItem và Cart liên quan
	        CartItem cartItem = repository.getById(cartItemId);
	        Cart cart = cartItem.getCart();

	        // Xóa CartItem
	        repository.delete(cartItem);

	        // Trả về danh sách CartItemDto của Cart mới
	        return ResponseEntity.ok(convert.convertToListCartItemDto(cart.getCartItems()));

	    } catch (Exception e) {
	        // Trả về phản hồi lỗi nếu xảy ra ngoại lệ
	        response.put("message", "Có lỗi xảy ra trong quá trình xóa sản phẩm khỏi giỏ hàng!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
}
