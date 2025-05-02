package vn.iotstar.TheLaApp.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private long reviewId;
    
    private int rating;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content; 
    private Timestamp reviewDate;
    private Boolean isModify;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImages;
    
    @OneToOne
    @JoinColumn(name = "order_detail_id", referencedColumnName = "orderDetailId")
    private OrderDetail orderDetail;

	public Review(int rating, String content, Timestamp reviewDate, User user, Product product, Boolean isModify,
			OrderDetail orderDetail) {
		super();
		this.rating = rating;
		this.content = content;
		this.reviewDate = reviewDate;
		this.user = user;
		this.product = product;
		this.isModify = isModify;
		this.orderDetail = orderDetail;
	} 
}
