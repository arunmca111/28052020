package com.aadp.vend.ws.io.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HumanUser extends User {
	protected HumanUser() {
	}

	public HumanUser(String userEmail, String userId, String firstName, String lastName, String encryptedPassword,
			Boolean emailVerificationStatus, long phoneNumber, String address) {
		super(userEmail, userId, firstName, lastName, encryptedPassword, emailVerificationStatus, address);
		this.phoneNumber = phoneNumber;
	}


	private long phoneNumber;

	@OneToMany(mappedBy = "humanUser")
	private List<MachineUser> machineUser = new ArrayList();

	public List<MachineUser> getMachineUser() {
		return machineUser;
	}
	
	public void addMachineUser(MachineUser machineUser) {
		this.machineUser.add(machineUser);
	}

}
