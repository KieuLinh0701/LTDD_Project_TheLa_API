package vn.iotstar.TheLaApp.controller;

import vn.iotstar.TheLaApp.dto.LoginDto;
import vn.iotstar.TheLaApp.dto.RegisterDto;
import vn.iotstar.TheLaApp.dto.ResetPasswordDto;
import vn.iotstar.TheLaApp.dto.UserDto;
import vn.iotstar.TheLaApp.dto.VerifyAccountDto;
import vn.iotstar.TheLaApp.entity.User;
import vn.iotstar.TheLaApp.repository.UserRepository;
import vn.iotstar.TheLaApp.service.implement.SendMailService;
import vn.iotstar.TheLaApp.service.implement.UserService;
import vn.iotstar.TheLaApp.util.JsonEncryptor;
import vn.iotstar.TheLaApp.util.PasswordUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
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
public class UserController {

  @Autowired
  private UserService userService;
  
  @PostMapping("/register")
  public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDto registerDto) throws Exception {
      return userService.register(registerDto);
  }
  
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
      return userService.login(loginDto);
  }
  
  @GetMapping("/send-email-verify-account")
  public ResponseEntity<Map<String, Object>> sendEmailVerifyAccount(
		  @RequestParam("email") String email, 
		  @RequestParam("feature") String feature) throws Exception{
	  return userService.sendEmailVerifyAccount(email, feature);
  }
  
  @GetMapping("/send-email-reset-email")
  public ResponseEntity<Map<String, Object>> sendEmailResetEmail(
		  @RequestParam("oldEmail") String oldEmail, 
		  @RequestParam("newEmail") String newEmail) throws Exception{
	  return userService.sendEmailResetEmail(oldEmail, newEmail);
  }
  
  @PutMapping("/verify-account")
  public ResponseEntity<Map<String, Object>> verifyAccount(@RequestBody VerifyAccountDto verifyAccountDto){
	  return userService.verifyAccount(verifyAccountDto);
  }
  
  @PutMapping("/reset-password")
  public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
	  return userService.resetPassword(resetPasswordDto);
  }
  
  @PutMapping("/save")
  public ResponseEntity<Map<String, Object>> save(@RequestBody UserDto dto) {
	  return userService.save(dto);
  }
}
