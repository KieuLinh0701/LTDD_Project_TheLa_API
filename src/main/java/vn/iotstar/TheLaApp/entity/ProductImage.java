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
@Table(name = "productImages")
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long imageId;
	
    private String image;
    private Boolean isMain;
    
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
}
