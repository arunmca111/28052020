package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "machine")
public class Machine extends Users implements Serializable {

	private static final long serialVersionUID = -4437279597077629495L;

	protected Machine() {
	}

	public Machine(String userEmail, String userId, String firstName, String lastName,
			String encryptedPassword, Boolean emailVerificationStatus, String address, String machineType,
			Boolean refrigerated, LocalDateTime boardChangeDate, LocalDateTime motorChangeDate,
			LocalDateTime lastServicedDate, String machineCode, String machineDescription) {
		super(userEmail, userId, firstName, lastName, encryptedPassword, emailVerificationStatus, address);

		this.machineType = machineType;
		this.isRefrigerated = refrigerated;
		this.boardChangeDate = boardChangeDate;
		this.motorChangeDate = motorChangeDate;
		this.lastServicedDate = lastServicedDate;
		this.machineCode = machineCode;
		this.machineDescription = machineDescription;
 	}
	
	
	@Column(nullable = false, length = 7,unique = true)
    private String machineCode;
	@Column(nullable = false, length = 7)
	private String machineType;
	@Column(nullable = false, length = 50)
	private String machineDescription;
	private Boolean isActive;
	private Boolean isRefrigerated;
	private LocalDateTime boardChangeDate;
	private LocalDateTime motorChangeDate;
	private LocalDateTime lastServicedDate;
	

	
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
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

	public String getMachineDescription() {
		return machineDescription;
	}

	public void setMachineDescription(String machineDescription) {
		this.machineDescription = machineDescription;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


}
