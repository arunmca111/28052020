package com.aadp.vend.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductsFavorite implements Serializable {

	private static final long serialVersionUID = -6399367939151962688L;

	@Id
	@Column(nullable = false, length = 20)
	private String productId;
	
	@Id
	@Column(nullable=false, length=20)
	private String userId;
	
	@Column(nullable=false)
	private boolean isFavorite;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	

}
