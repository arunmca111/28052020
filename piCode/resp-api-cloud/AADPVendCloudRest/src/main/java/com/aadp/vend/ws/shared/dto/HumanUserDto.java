package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class HumanUserDto implements Serializable{

	private static final long serialVersionUID = 6835192601898364280L;
	
	private String email;
    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime userCreatedDate;
    private LocalDateTime userLastUpdatedDate;
    
    private String encryptedPassword;
    private String emailVerificationToken;
    private String password;
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
	
	public LocalDateTime getUserCreatedDate() {
		return userCreatedDate;
	}
	public void setUserCreatedDate(LocalDateTime userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}
	
	public LocalDateTime getUserLastUpdatedDate() {
		return userLastUpdatedDate;
	}
	public void setUserLastUpdatedDate(LocalDateTime userLastUpdatedDate) {
		this.userLastUpdatedDate = userLastUpdatedDate;
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
	
	
    
    
    
}
