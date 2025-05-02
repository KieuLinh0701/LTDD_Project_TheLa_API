package vn.iotstar.TheLaApp.entity;

import java.sql.Timestamp;

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
@Table(name = "cartItems")
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long cartItemId;
	
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productSizeId", nullable = false)
    private ProductSize productSize;
    
    private Timestamp addDate;

	public CartItem(int quantity, Cart cart, ProductSize productSize, Timestamp addDate) {
		super();
		this.quantity = quantity;
		this.cart = cart;
		this.productSize = productSize;
		this.addDate = addDate;
	}
}
