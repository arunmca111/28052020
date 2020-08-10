package com.aadpHome.solutions.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Device implements Serializable {

	
	private static final long serialVersionUID = -3772960599034356498L;

	protected Device() {
	}

	@Id
	@Column(nullable = false, length = 120)
	private String deviceId;
	
	@Column(nullable = false, length = 50)
	private String userId;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false, length = 1)
	private int onOffState;

	@Column(nullable = false, length = 150)
	private String imageUrl;
	
	@Column(nullable = false)
	private boolean isFavorite = false;

	@UpdateTimestamp
	private LocalDateTime deviceLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime deviceCreatedDate;

	
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

	public LocalDateTime getDeviceLastUpdatedDate() {
		return deviceLastUpdatedDate;
	}

	public void setDeviceLastUpdatedDate(LocalDateTime deviceLastUpdatedDate) {
		this.deviceLastUpdatedDate = deviceLastUpdatedDate;
	}

	public LocalDateTime getDeviceCreatedDate() {
		return deviceCreatedDate;
	}

	public void setDeviceCreatedDate(LocalDateTime deviceCreatedDate) {
		this.deviceCreatedDate = deviceCreatedDate;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public String toString() {
		return String.format("Devices[%s]", title);
	}


}