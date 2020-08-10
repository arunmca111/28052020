package com.aadpHome.solutions.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "UsersHome")
public  class Users implements Serializable {

	private static final long serialVersionUID = -5393439084361724294L;

	protected Users() {
	}

	public Users(String userEmail, String userId, String firstName, String lastName, String encryptedPassword,
			Boolean emailVerificationStatus, String userAddress) {
		this.email = userEmail;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailVerificationStatus = emailVerificationStatus;
		this.encryptedPassword = encryptedPassword;

	}

	@Id
	@Column(nullable = false, length = 120)
	private String email;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 50)
	private String phoneNumber;

	@UpdateTimestamp
	private LocalDateTime userLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime userCreatedDate;

	@Column(nullable = false)
	private String encryptedPassword;

	private String emailVerificationToken;
	
	@Column(nullable = false) 
	private String password;

	@Column(nullable = false, columnDefinition = "BOOLEAN")
	private Boolean emailVerificationStatus = false;
	


	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	@Override
	public String toString() {
		return String.format("User[%s]", email);
	}
}