package com.aadp.vend.ws.ui.model.request;

import java.util.Date;
import java.util.List;

public class OrderDetailRequestModel {
	
	private String ordersId;
	private String userId;
	private String machineCode;
	private double amount;
	private UpiResponseModel upiTranResponse;
	private Date dateTime;
	private List<CartItemsModel> cartItems;
	private UpiResponseModel upiTransactionResponse;
	
	

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
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public UpiResponseModel getUpiTransactionResponse() {
		return upiTransactionResponse;
	}
	public void setUpiTransactionResponse(UpiResponseModel upiTransactionResponse) {
		this.upiTransactionResponse = upiTransactionResponse;
	}
	public UpiResponseModel getUpiTranResponse() {
		return upiTranResponse;
	}
	public void setUpiTranResponse(UpiResponseModel upiTranResponse) {
		this.upiTranResponse = upiTranResponse;
	}
	
	
	

}
