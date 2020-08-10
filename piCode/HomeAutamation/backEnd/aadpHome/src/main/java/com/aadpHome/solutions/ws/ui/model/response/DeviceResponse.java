package com.aadpHome.solutions.ws.ui.model.response;

public class DeviceResponse {
	
	private String deviceId;
	private String userId;
	private String title;
	private String description;
	private int onOffState;
	private String imageUrl;
	private boolean isFavorite;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getOnOffState() {
		return onOffState;
	}
	public void setOnOffState(int onOffState) {
		this.onOffState = onOffState;
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
	

}
