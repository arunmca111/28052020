package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "machineUser")
public class MachineUserEnitity extends Users implements Serializable {

	private static final long serialVersionUID = -4437279597077629495L;

	protected MachineUserEnitity() {
	}

	public MachineUserEnitity(String userEmail, String userId, String firstName, String lastName,
			String encryptedPassword, Boolean emailVerificationStatus, String address, String machineType,
			Boolean refrigerated, LocalDateTime boardChangeDate, LocalDateTime motorChangeDate,
			LocalDateTime lastServicedDate, int maxColumn) {
		super(userEmail, userId, firstName, lastName, encryptedPassword, emailVerificationStatus, address);

		this.machineType = machineType;
		this.isRefrigerated = refrigerated;
		this.boardChangeDate = boardChangeDate;
		this.motorChangeDate = motorChangeDate;
		this.lastServicedDate = lastServicedDate;
		this.maxColumn = maxColumn;
	}

	private String machineType;

	private Boolean isRefrigerated;
	private LocalDateTime boardChangeDate;
	private LocalDateTime motorChangeDate;
	private LocalDateTime lastServicedDate;
	private int maxColumn;

	@ManyToOne
	private HumanUserEntity humanUser;

	public HumanUserEntity getHumanUser() {
		return humanUser;
	}

	public void setHumanUser(HumanUserEntity humanUser) {
		this.humanUser = humanUser;
	}

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

	public int getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}

}
