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
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
	private Long addressId;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String city;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String district;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String commune;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String detail;
	
	@OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

	public Address(String city, String district, String commune, String detail, User user) {
		super();
		this.city = city;
		this.district = district;
		this.commune = commune;
		this.detail = detail;
		this.user = user;
	}
}
