package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Products implements Serializable {

	
	private static final long serialVersionUID = -3772960599034356498L;

	protected Products() {
	}

	public Products(String id,String userId,  String title, String description, double price, String imageUrl,
			boolean isFavorite) {
		this.id = id;
		this.userId=userId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.isFavorite = isFavorite;

	}

	@Id
	@Column(nullable = false, length = 120)
	private String id;
	
	@Column(nullable = false, length = 50)
	private String userId;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false, length = 18)
	private double price;

	@Column(nullable = false, length = 150)
	private String imageUrl;

	@UpdateTimestamp
	private LocalDateTime productLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime productCreatedDate;

	@Column(nullable = false)
	private boolean isFavorite = false;

	public String getId() {
		return id;
	}

	public void setId(String id) { 
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getProductLastUpdatedDate() {
		return productLastUpdatedDate;
	}

	public void setProductLastUpdatedDate(LocalDateTime productLastUpdatedDate) {
		this.productLastUpdatedDate = productLastUpdatedDate;
	}

	public LocalDateTime getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(LocalDateTime productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public String toString() {
		return String.format("Proucts[%s]", title);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}