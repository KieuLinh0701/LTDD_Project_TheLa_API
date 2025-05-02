package vn.iotstar.TheLaApp.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;

public interface IUserService {
	ResponseEntity<Map<String, Object>> register(RegisterDto registerDto) throws Exception;
	ResponseEntity<Map<String, Object>> sendEmailVerifyAccount(String email, String feature) throws Exception;
	ResponseEntity<Map<String, Object>> verifyAccount(VerifyAccountDto verifyAccountDto);
	ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception;
	ResponseEntity<Map<String, Object>> resetPassword(ResetPasswordDto resetPasswordDto);
	ResponseEntity<Map<String, Object>> sendEmailResetEmail(String oldEmail, String newEmail) throws Exception;
	ResponseEntity<Map<String, Object>> save(UserDto dto);
}
