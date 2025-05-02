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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotions")
public class Promotion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long promotionId;
	
	@Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    private String image;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;
    private Double discountPercentage;
    private Double minimumOrderValue;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long quantity;
    private Long quantityUsed;
    private Boolean isActive;
    private Boolean isDelete;
    
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders;
}
