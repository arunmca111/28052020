package com.aadp.vend.ws.io.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MachineUser extends User {
	protected MachineUser() {
	}

	public MachineUser(String userEmail, String userId, String firstName, String lastName, String encryptedPassword,
			Boolean emailVerificationStatus,String address, String machineType, Boolean refrigerated,
			LocalDateTime boardChangeDate, LocalDateTime motorChangeDate, LocalDateTime lastServicedDate,
			long maxColumn) {
		super(userEmail, userId, firstName, lastName, encryptedPassword, emailVerificationStatus, address);
		this.machineType = machineType;
		this.refrigerated = refrigerated;
		this.boardChangeDate = boardChangeDate;
		this.motorChangeDate = motorChangeDate;
		this.lastServicedDate = lastServicedDate;
		this.maxColumn = maxColumn;
	}

	private String machineType;
	private Boolean refrigerated;
	private LocalDateTime boardChangeDate;
	private LocalDateTime motorChangeDate;
	private LocalDateTime lastServicedDate;
	private long maxColumn;
	
	@ManyToOne
	private HumanUser humanUser;

	public HumanUser getHumanUser() {
		return humanUser;
	}

	public void setHumanUser(HumanUser humanUser) {
		this.humanUser = humanUser;
	}

}
