package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;

public class ProductsDto implements Serializable {

	private static final long serialVersionUID = -5020622906546337589L;
	
	private String machineSlotId;
	private String productId;
	private String userId;
	private String title;
	private String description;
	private double price;
	private String imageUrl;
	private boolean isFavorite;
	

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
	public boolean isFavorite() {
		return isFavorite;
	}
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMachineslotId() {
		return machineSlotId;
	}
	public void setMachineslotId(String machineSlotId) {
		this.machineSlotId = machineSlotId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	


}
