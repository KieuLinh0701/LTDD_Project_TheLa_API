package vn.iotstar.TheLaApp.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderDetails")
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long orderDetailId;
	
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "productSizeId", nullable = true)
    private ProductSize productSize;
    
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
    
    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    private Review review;

	public OrderDetail(Integer quantity, BigDecimal price, ProductSize productSize, Order order) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.productSize = productSize;
		this.order = order;
	}
}
