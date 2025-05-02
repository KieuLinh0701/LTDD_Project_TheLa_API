package vn.iotstar.TheLaApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviewImages")
public class ReviewImage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long imageId;
	
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "reviewId", nullable = false)
    private Review review;

	public ReviewImage(String image, Review review) {
		super();
		this.image = image;
		this.review = review;
	}
}
