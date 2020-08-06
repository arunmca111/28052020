package com.aadp.vend.ws.ui.model.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.aadp.vend.ws.ui.model.request.CartItemsModel;

public class OrdersFetchResponse implements Serializable{
	

	private static final long serialVersionUID = 1493712766075522129L;
	
	private String ordersId;
	private String machineCode;
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
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	
	
	

}
