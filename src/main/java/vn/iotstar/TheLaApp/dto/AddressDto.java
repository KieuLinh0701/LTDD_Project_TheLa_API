package vn.iotstar.TheLaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	private Long addressId;
	private String city;
	private String district;
	private String commune;
	private String detail;
}
