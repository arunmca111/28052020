package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;

public class FavoriteDto  implements Serializable{
	
	private static final long serialVersionUID = 7119144391860088833L;
	private String productId;
	private String userId;
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
