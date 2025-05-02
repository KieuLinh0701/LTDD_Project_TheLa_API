package vn.iotstar.TheLaApp.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
	private long reviewId;
    private int rating;
    private String content;
    private Timestamp reviewDate;
    private Boolean isModify;
    private UserDto user;
    
    private List<ReviewImageDto> reviewImages;
}
