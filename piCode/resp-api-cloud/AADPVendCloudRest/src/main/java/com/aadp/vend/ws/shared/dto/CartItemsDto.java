package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;

public class CartItemsDto implements Serializable{
	
	private static final long serialVersionUID = 6820758335911112427L;
	
	private String cartId;
	private String slotId;
	private String prodId;
	private String title;
	private int quantity;
	private double price;
	

	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	
	
	
	
	

}
