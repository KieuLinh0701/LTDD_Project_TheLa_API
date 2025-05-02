package vn.iotstar.TheLaApp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String image;
    private Boolean isActive;
    
    private CartDto cart;
    private AddressDto address;
}