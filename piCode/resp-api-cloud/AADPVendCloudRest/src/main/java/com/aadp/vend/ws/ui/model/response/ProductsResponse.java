package com.aadp.vend.ws.ui.model.response;

public class ProductsResponse {
	
	private String productId;
	private String machineSlotId;
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
	public String getMachineSlotId() {
		return machineSlotId;
	}
	public void setMachineSlotId(String machineSlotId) {
		this.machineSlotId = machineSlotId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	

}
