package exam.portal.tn.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Product {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long productId;
	private String productName;
	private String productDescription;
	private Double productDiscountPrice;
	private Double productActualPrice;
	private String fileName;
	
	@JsonBackReference(value = "category")
	@ManyToOne
	private Category category;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tag_products", joinColumns = @JoinColumn(name = "tag_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Tag> tags;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Comment> comments;
	
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="product_images",joinColumns= {
			@JoinColumn(name="product_id")
	},inverseJoinColumns= {
			@JoinColumn(name="image_id")
	})
	private Set<ImageModel> productImages;
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Product(Long productId, String productName, String productDescription, Double productDiscountPrice,
			Double productActualPrice, String fileName, Category category, List<Tag> tags, List<Comment> comments,
			Set<ImageModel> productImages) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productDiscountPrice = productDiscountPrice;
		this.productActualPrice = productActualPrice;
		this.fileName = fileName;
		this.category = category;
		this.tags = tags;
		this.comments = comments;
		this.productImages = productImages;
	}





	public Product(String productName, String productDescription, Double productDiscountPrice,
			Double productActualPrice, String fileName, Category category, List<Tag> tags, List<Comment> comments,
			Set<ImageModel> productImages) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productDiscountPrice = productDiscountPrice;
		this.productActualPrice = productActualPrice;
		this.fileName = fileName;
		this.category = category;
		this.tags = tags;
		this.comments = comments;
		this.productImages = productImages;
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

	public Set<ImageModel> getProductImages() {
		return productImages;
	}
	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	
	
	
	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public List<Tag> getTags() {
		return tags;
	}



	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public void addCommentToProduct(Comment comment) {
		if (getComments() == null) {
			this.comments = new ArrayList<>();
		}
		getComments().add(comment);
		comment.setProduct(this);
	}

	public void addTag(Tag tag) {
		if (getTags() == null) {
			this.tags = new ArrayList<>();
		}
		if (!getTags().contains(tag)) {
			getTags().add(tag);
		}
	}

}
