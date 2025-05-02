package vn.iotstar.TheLaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class VerifyAccountDto {

	private String email;
	private String code;
	private int otpDuration;
}
