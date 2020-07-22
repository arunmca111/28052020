package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrdersDto implements Serializable{
	

	private static final long serialVersionUID = 1493712766075522129L;
	
	private String ordersId;
	private double amount;
	private Date dateTime;
	private List<CartItemsDto> cartItems;
	
	
	
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
	public List<CartItemsDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemsDto> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	

}
