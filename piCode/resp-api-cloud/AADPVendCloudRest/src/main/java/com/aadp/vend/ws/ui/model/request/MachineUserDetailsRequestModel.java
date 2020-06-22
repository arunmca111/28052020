package com.aadp.vend.ws.ui.model.request;

import java.time.LocalDateTime;

public class MachineUserDetailsRequestModel {

	private String email;
	private String humanUserEmail;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String password;

	private Boolean isRefrigerated;
	private LocalDateTime boardChangeDate;
	private LocalDateTime motorChangeDate;
	private LocalDateTime lastServicedDate;
	private int maxColumn;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHumanUserEmail() {
		return humanUserEmail;
	}

	public void setHumanUserEmail(String humanUserEmail) {
		this.humanUserEmail = humanUserEmail;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsRefrigerated() {
		return isRefrigerated;
	}

	public void setIsRefrigerated(Boolean isRefrigerated) {
		this.isRefrigerated = isRefrigerated;
	}

	public LocalDateTime getBoardChangeDate() {
		return boardChangeDate;
	}

	public void setBoardChangeDate(LocalDateTime boardChangeDate) {
		this.boardChangeDate = boardChangeDate;
	}

	public LocalDateTime getMotorChangeDate() {
		return motorChangeDate;
	}

	public void setMotorChangeDate(LocalDateTime motorChangeDate) {
		this.motorChangeDate = motorChangeDate;
	}

	public LocalDateTime getLastServicedDate() {
		return lastServicedDate;
	}

	public void setLastServicedDate(LocalDateTime lastServicedDate) {
		this.lastServicedDate = lastServicedDate;
	}

	public int getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}

}
