package vn.iotstar.TheLaApp.service.implement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.Cart;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.CartRepository;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.IUserService;
import vn.iotstar.TheLaApp.util.ConvertToDto;
import vn.iotstar.TheLaApp.util.JsonEncryptor;
import vn.iotstar.TheLaApp.util.PasswordUtils;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	  
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private CartRepository cartRepository;
	  
	@Autowired
	private PasswordUtils passwordUtils;
	  
	@Autowired
	private JsonEncryptor jsonEncryptor;
	
	@Autowired
	private ConvertToDto convert;
	
	public ResponseEntity<Map<String, Object>> register(RegisterDto registerDto) throws Exception {
	    Map<String, Object> response = new HashMap<>();
	    
	    String name = registerDto.getName();
	    String email = registerDto.getEmail();
	    String password = registerDto.getPassword();

	    System.out.println("Checking email: " + email);
	    
	    User existingUser = userRepository.findByEmail(email);
	    System.out.println("Existing user: " + existingUser);
	    
	    if (existingUser != null) {
	        response.put("message", "Email đã tồn tại. Vui lòng sử dụng email khác!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    String hashedPassword = passwordUtils.hashPassword(password);
	    String code = sendMailService.getRandom();
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("otp", code);
	    String encryptedCode = jsonEncryptor.encrypt(jsonObject.toString());

	    User user = new User(
	            name, email, hashedPassword, encryptedCode, null, null, "CUSTOMER",
	            null, new Timestamp(System.currentTimeMillis()), false
	    );

	    String subject  = "Xác Thực Tài Khoản";
        String message = "Hi " + user.getName() + ",\n" +
                "Hãy sử dụng mã bên dưới để xác nhận tài khoản của bạn:\n" +
                code + "\n" +
                "Cảm ơn bạn đã tham gia cùng chúng tôi!";

	    if (sendMailService.sendEmail(email, subject, message)) {
	        User newUser = userRepository.save(user);
	        if (newUser != null) {
	        	Cart cart = new Cart(newUser);
	        	cartRepository.save(cart);
	            response.put("message", "Đăng ký thành công! Vui lòng kiểm tra email để xác thực tài khoản!");
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("message", "Đã xảy ra lỗi khi tạo tài khoản. Vui lòng thử lại!");
	            return ResponseEntity.badRequest().body(response);
	        }
	    } else {
	        response.put("message", "Gửi email xác thực thất bại. Vui lòng thử lại!");
	        return ResponseEntity.badRequest().body(response);
	    }
	}
	
	public ResponseEntity<Map<String, Object>> sendEmailVerifyAccount(String email, String feature) throws Exception {
	    Map<String, Object> response = new HashMap<>();

	    // Kiểm tra email có hợp lệ không
	    if (email == null || email.trim().isEmpty()) {
	        response.put("message", "Email không hợp lệ!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    // Kiểm tra feature có hợp lệ không
	    if (feature == null || feature.trim().isEmpty()) {
	        response.put("message", "Tính năng không hợp lệ!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    // Tạo mã ngẫu nhiên
	    String code = sendMailService.getRandom();

	    // Tìm người dùng
	    User existingUser = userRepository.findByEmail(email);
	    if (existingUser == null) {
	        response.put("message", "Không tìm thấy người dùng với email này!");
	        return ResponseEntity.badRequest().body(response);
	    }


	    // Xác định nội dung email
	    String subject;
	    String message;
	    switch (feature) {
	        case "Login":
	        case "Register":
	            subject = "Xác Thực Tài Khoản";
	            message = "Hi " + existingUser.getName() + ",\n" +
	                    "Hãy sử dụng mã bên dưới để xác nhận tài khoản của bạn:\n" +
	                    code + "\nCảm ơn bạn đã tham gia cùng chúng tôi!";
	            break;
	        case "ForgotPassword":
	        case "ChangePassword":
	            subject = "Đặt Lại Mật Khẩu";
	            message = "Hi " + existingUser.getName() + ",\n" +
	                    "Hãy sử dụng mã bên dưới để đặt lại mật khẩu của bạn:\n" +
	                    code + "\nNếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.";
	            break;
	        default:
	            response.put("message", "Tính năng không hợp lệ.");
	            return ResponseEntity.badRequest().body(response);
	    }

	    // Gửi email
	    boolean isSent = sendMailService.sendEmail(email, subject, message);
	    if (!isSent) {
	        response.put("message", "Gửi email xác thực không thành công. Vui lòng thử lại sau!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    // Lưu mã vào database sau khi gửi thành công
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("otp", code);
	    String encryptedCode = jsonEncryptor.encrypt(jsonObject.toString());
	    existingUser.setCode(encryptedCode);
	    existingUser.setCreateCode(new Timestamp(System.currentTimeMillis()));
	    userRepository.save(existingUser);

	    response.put("message", "Email xác thực đã được gửi!");
	    return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<Map<String, Object>> sendEmailResetEmail(String oldEmail, String newEmail) throws Exception {
	    Map<String, Object> response = new HashMap<>();

	    // Tạo mã ngẫu nhiên
	    String code = sendMailService.getRandom();

	    // Tìm người dùng
	    User existingUser = userRepository.findByEmail(oldEmail);
	    if (existingUser == null) {
	        response.put("message", "Không tìm thấy người dùng để đặt lại email!");
	        return ResponseEntity.badRequest().body(response);
	    }
	    
	    if (userRepository.findByEmail(newEmail) != null) {
	    	response.put("message", "Email này đã được liên kết với tài khoản khác. Vui lòng nhập một email khác!");
	        return ResponseEntity.badRequest().body(response);
	    }


	    // Xác định nội dung email
	    String subject = "Xác Thực Tài Khoản";
	    String message = "Hi " + existingUser.getName() + ",\n" +
                "Hãy sử dụng mã bên dưới để xác nhận tài khoản của bạn:\n" +
                code + "\nCảm ơn bạn đã tham gia cùng chúng tôi!";
	    

	    // Gửi email
	    boolean isSent = sendMailService.sendEmail(newEmail, subject, message);
	    if (!isSent) {
	        response.put("message", "Gửi email xác thực không thành công. Vui lòng kiểm tra lại email của bạn hoặc thử lại sau!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    // Lưu mã vào database sau khi gửi thành công
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("otp", code);
	    String encryptedCode = jsonEncryptor.encrypt(jsonObject.toString());
	    existingUser.setCode(encryptedCode);
	    existingUser.setCreateCode(new Timestamp(System.currentTimeMillis()));
	    userRepository.save(existingUser);

	    response.put("message", "Email xác thực đã được gửi!");
	    return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<Map<String, Object>> verifyAccount(VerifyAccountDto verifyAccountDto) {
		Map<String, Object> response = new HashMap<>();
		
		String email = verifyAccountDto.getEmail();
		String code = verifyAccountDto.getCode();
		int otpDuration = verifyAccountDto.getOtpDuration();
		
		// Tìm người dùng
	    User user = userRepository.findByEmail(email);
	    if (user != null && user.getCreateCode() != null &&
                ((System.currentTimeMillis() - user.getCreateCode().getTime()) / 1000 <= otpDuration)) {
	        try {
                // Giải mã chuỗi mã hóa từ user.getCode()
                String decryptedCode = JsonEncryptor.decrypt(user.getCode());

                // Lấy giá trị của khóa "otp" từ chuỗi JSON đã giải mã
                String codeFromUser = new JSONObject(decryptedCode).getString("otp");

                if (code.equals(codeFromUser)) {
                    user.setIsActive(true);
                    userRepository.save(user);
                        
                    response.put("message", "Xác thực thành công!");
            	    return ResponseEntity.ok(response);
                } else {
                	response.put("message", "Mã OTP không chính xác, vui lòng kiểm tra lại!");
        	        return ResponseEntity.badRequest().body(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.put("message", "Lỗi xử lý mã OTP. Vui lòng thử lại!");
    	        return ResponseEntity.badRequest().body(response);
            }
        } else {
        	response.put("message", "Mã OTP của bạn đã hết hạn. Vui lòng nhấn 'Gửi lại' để nhận mã mới và thử lại!");
	        return ResponseEntity.badRequest().body(response);
        }
	}
	
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
	    
	    String email = loginDto.getEmail();
	    String password = loginDto.getPassword();
	    
	    User user = userRepository.findByEmail(email);
	    
	    if (user != null 
	    		&& user.getPassword() != null 
	    		&& PasswordUtils.verifyPassword(password, user.getPassword())) {
	    	return ResponseEntity.ok(convert.convertToUserDTO(user));
	    } else {
	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("Email hoặc mật khẩu không đúng");
	    }
	}
	
	public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		Map<String, Object> response = new HashMap<>();
	    
	    String email = resetPasswordDto.getEmail();
	    String password = resetPasswordDto.getPassword();
	    
	    User user = userRepository.findByEmail(email);
	    
	    if (user != null) {
	    	String hashedPassword = passwordUtils.hashPassword(password);
	    	user.setPassword(hashedPassword);
	    	
	    	userRepository.save(user);
	    	
	    	response.put("message", "Đặt lại mật khẩu thành công!");
	    	return ResponseEntity.ok(response);
	    } else {
	    	response.put("message", "Lỗi xử lý mật khẩu. Vui lòng thử lại!");
	        return ResponseEntity.badRequest().body(response);
	    }
	}
	
	public ResponseEntity<Map<String, Object>> save(UserDto dto) {
		Map<String, Object> response = new HashMap<>();
		
		Long userId = dto.getUserId();
		
		User user = userRepository.getById(userId);
		
		if (user != null) {
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setImage(dto.getImage());
			user.setPhone(dto.getPhone());
			
	    	userRepository.save(user);
	    	
	    	response.put("message", "Xác thực thành công!");
	    	return ResponseEntity.ok(response);
	    } else {
	    	response.put("message", "Không tìm thấy người dùng!");
	        return ResponseEntity.badRequest().body(response);
	    }

	}
}
