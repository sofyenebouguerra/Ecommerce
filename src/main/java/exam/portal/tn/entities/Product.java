package exam.portal.tn.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long productId;
	private String productName;
	private String productDescription;
	private Double productDiscountPrice;
	private Double productActualPrice;
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="product_images",joinColumns= {
			@JoinColumn(name="product_id")
	},inverseJoinColumns= {
			@JoinColumn(name="image_id")
	}
	)

	private Set<ImageModel> productImages;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String productName, String productDescription, Double productDiscountPrice,
			Double productActualPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productDiscountPrice = productDiscountPrice;
		this.productActualPrice = productActualPrice;
	}
	public Product(Long productId, String productName, String productDescription, Double productDiscountPrice,
			Double productActualPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productDiscountPrice = productDiscountPrice;
		this.productActualPrice = productActualPrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getProductDiscountPrice() {
		return productDiscountPrice;
	}
	public void setProductDiscountPrice(Double productDiscountPrice) {
		this.productDiscountPrice = productDiscountPrice;
	}
	public Double getProductActualPrice() {
		return productActualPrice;
	}
	public void setProductActualPrice(Double productActualPrice) {
		this.productActualPrice = productActualPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<ImageModel> getProductImages() {
		return productImages;
	}
	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	

}