package vn.iotstar.TheLaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.iotstar.TheLaApp.entity.Review;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImageDto {
	private Long imageId;
    private String image;
}
