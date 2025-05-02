package vn.iotstar.TheLaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

	private String name;
	private String email;
	private String password;
}
