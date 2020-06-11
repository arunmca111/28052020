package com.aadp.vend.ws.io.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	protected User() {
	}

	public User(String userEmail, String userId, String firstName, String lastName, String encryptedPassword,
			Boolean emailVerificationStatus, String userAddress) {
		this.userEmail = userEmail;
		this.userId = userId;
		this.userFirstname = firstName;
		this.userLastName = lastName;
		this.userEncryptedPassword = encryptedPassword;
		this.userEmailVerificationStatus = emailVerificationStatus;
		this.userAddress = userAddress;

	}

	
	@Id
	@Column(nullable = false, length = 120)
	private String userEmail;
	
	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String userFirstname;

	@Column(nullable = false, length = 50)
	private String userLastName;

	@Column(nullable = false)
	private Boolean userEmailVerificationStatus = false;
	
	@UpdateTimestamp
	private LocalDateTime userLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime userCreatedDate;
	
	@Column(nullable = false)
	private String userEncryptedPassword;
	
	@Column(nullable = false)
	private String userAddress;

	private String userEmailVerificationToken;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDateTime getUserLastUpdatedDate() {
		return userLastUpdatedDate;
	}

	public void setUserLastUpdatedDate(LocalDateTime userLastUpdatedDate) {
		this.userLastUpdatedDate = userLastUpdatedDate;
	}

	public LocalDateTime getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(LocalDateTime userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public String getUserEncryptedPassword() {
		return userEncryptedPassword;
	}

	public void setUserEncryptedPassword(String userEncryptedPassword) {
		this.userEncryptedPassword = userEncryptedPassword;
	}

	public Boolean getUserEmailVerificationStatus() {
		return userEmailVerificationStatus;
	}

	public void setUserEmailVerificationStatus(Boolean userEmailVerificationStatus) {
		this.userEmailVerificationStatus = userEmailVerificationStatus;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return String.format("User[%s]", userEmail);
	}
}