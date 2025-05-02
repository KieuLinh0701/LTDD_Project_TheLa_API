package vn.iotstar.TheLaApp.entity;

import java.math.BigDecimal;
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
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long orderId;
	
    private BigDecimal totalPrice;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String note;
    private Timestamp createDate;
    private Timestamp deliveryDate;
    
    @Column(columnDefinition = "NVARCHAR(255)")
    private String deliveryAddress;
    private String deliveryPhone;
    private String status;
    
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "promotionId", nullable = true)
    private Promotion promotion;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "paymentMethodId", nullable = true)
    private PaymentMethod paymentMethod;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

	public Order(BigDecimal totalPrice, String note, Timestamp createDate, String deliveryAddress, String deliveryPhone,
			String status, String name, Promotion promotion, User user, PaymentMethod paymentMethod) {
		super();
		this.totalPrice = totalPrice;
		this.note = note;
		this.createDate = createDate;
		this.deliveryAddress = deliveryAddress;
		this.deliveryPhone = deliveryPhone;
		this.status = status;
		this.name = name;
		this.promotion = promotion;
		this.user = user;
		this.paymentMethod = paymentMethod;
	}
}
