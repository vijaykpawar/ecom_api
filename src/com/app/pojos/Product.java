package com.app.pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(value = { "date_created", "date_updated" }, allowGetters = true)
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column(name = "name")
	private String name;

	@Column(name = "desc1")
	private String desc;
	@Column(name = "price")
	private double price;
	@Column(name = "is_available")
	private boolean isAvailable;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "image_1")
	private String image1;
	@Column(name = "image_2")
	private String image2;
	@Column(name = "image_3")
	private String image3;
	@Column(name = "image_4")
	private String image4;

	@JsonIgnore
	private transient MultipartFile image1File;
	@JsonIgnore
	private transient MultipartFile image2File;
	@JsonIgnore
	private transient MultipartFile image3File;
	@JsonIgnore
	private transient MultipartFile image4File;

	private transient long categoryId;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	private transient long subCategoryId;

	@Column(name = "date_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public MultipartFile getImage1File() {
		return image1File;
	}

	public void setImage1File(MultipartFile image1File) {
		this.image1File = image1File;
	}

	public MultipartFile getImage2File() {
		return image2File;
	}

	public void setImage2File(MultipartFile image2File) {
		this.image2File = image2File;
	}

	public MultipartFile getImage3File() {
		return image3File;
	}

	public void setImage3File(MultipartFile image3File) {
		this.image3File = image3File;
	}

	public MultipartFile getImage4File() {
		return image4File;
	}

	public void setImage4File(MultipartFile image4File) {
		this.image4File = image4File;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", desc=" + desc + ", price=" + price
				+ ", isAvailable=" + isAvailable + ", quantity=" + quantity + ", image1=" + image1 + ", image2="
				+ image2 + ", image3=" + image3 + ", image4=" + image4 + ", image1File=" + image1File + ", image2File="
				+ image2File + ", image3File=" + image3File + ", image4File=" + image4File + ", categoryId="
				+ categoryId + ", category=" + category + ", subCategory=" + subCategory + ", user=" + user
				+ ", dateCreated=" + dateCreated + ", subCategoryId=" + subCategoryId + ", dateUpdated=" + dateUpdated
				+ ", getProductId()=" + getProductId() + ", getName()=" + getName() + ", getDesc()=" + getDesc()
				+ ", getPrice()=" + getPrice() + ", isAvailable()=" + isAvailable() + ", getQuantity()=" + getQuantity()
				+ ", getCategoryId()=" + getCategoryId() + ", getImage1()=" + getImage1() + ", getImage2()="
				+ getImage2() + ", getImage3()=" + getImage3() + ", getImage4()=" + getImage4() + ", getDateCreated()="
				+ getDateCreated() + ", getSubCategoryId()=" + getSubCategoryId() + ", getDateUpdated()="
				+ getDateUpdated() + ", getImage1File()=" + getImage1File() + ", getImage2File()=" + getImage2File()
				+ ", getImage3File()=" + getImage3File() + ", getImage4File()=" + getImage4File() + ", getCategory()="
				+ getCategory() + ", getUser()=" + getUser() + ", getSubCategory()=" + getSubCategory()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
