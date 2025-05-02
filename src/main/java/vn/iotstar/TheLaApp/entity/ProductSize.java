package vn.iotstar.TheLaApp.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productSizes")
public class ProductSize {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long productSizeId;
	
	private BigDecimal price;
	
	@ManyToOne
    @JoinColumn(name = "sizeId", nullable = false)
    private Size size;
	
	@ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
	
	@OneToMany(mappedBy = "productSize", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "productSize", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

}
