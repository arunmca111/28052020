package com.aadp.vend.ws.ui.model.request;

import java.util.Date;
import java.util.List;

public class OrderDetailRequestModel {
	
	private String ordersId;
	private String userId;
	private double amount;
	private Date dateTime;
	private List<CartItemsModel> cartItems;
	
	

	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public List<CartItemsModel> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemsModel> cartItems) {
		this.cartItems = cartItems;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
