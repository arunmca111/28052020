package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Orders implements Serializable {

	private static final long serialVersionUID = -6577362906816027708L;

	protected Orders() {
	}

	public Orders(String ordersId, double amount, Date dateTime, List<CartItems> cartItems) {
		this.ordersId = ordersId;
		this.amount = amount;
		this.dateTime = dateTime;
		this.cartItems = cartItems;
	}

	@Id
	@Column(nullable = false, length = 120)
	private String ordersId;
	
	@Id
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, length = 7)
	private String machineCode;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private List<CartItems> cartItems;
	
	@OneToOne
	private PaymentTran payment;
	
	@Column(nullable = false, length = 18)
	private double amount;

	@Column(nullable = false)
	private Date dateTime;
	
	@UpdateTimestamp
	private LocalDateTime orderLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime orderCreatedDate;

	
	public void addCartItem(CartItems cartItems) {
		this.cartItems.add(cartItems);
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

	public LocalDateTime getOrderLastUpdatedDate() {
		return orderLastUpdatedDate;
	}

	public void setOrderLastUpdatedDate(LocalDateTime orderLastUpdatedDate) {
		this.orderLastUpdatedDate = orderLastUpdatedDate;
	}

	public LocalDateTime getOrderCreatedDate() {
		return orderCreatedDate;
	}

	public void setOrderCreatedDate(LocalDateTime orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	@Override
	public String toString() {
		return String.format("Orders[%s]", ordersId);
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
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

	public PaymentTran getPayment() {
		return payment;
	}

	public void setPayment(PaymentTran payment) {
		this.payment = payment;
	}
}