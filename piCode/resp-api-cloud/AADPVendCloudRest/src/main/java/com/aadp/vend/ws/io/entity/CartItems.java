package com.aadp.vend.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItems implements Serializable {

	private static final long serialVersionUID = 7662682803754292278L;

	@Id
	@Column(nullable = false, length = 120)
	private String cartId;
	
	@Column(nullable = false, length = 120)
	private String prodId;
	
	@Column(length=45, nullable=false)
	private String title;
	
	@Column(length=4, nullable=false)
	private int quantity;
	
	@Column(length=18, nullable=false)
	private double price;
	
	@ManyToOne
	private Orders order;
	

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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

}
