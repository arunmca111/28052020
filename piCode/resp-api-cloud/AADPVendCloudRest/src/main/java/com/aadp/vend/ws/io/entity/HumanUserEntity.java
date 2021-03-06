package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="humanUser")
public class HumanUserEntity extends Users implements Serializable {
	
	private static final long serialVersionUID = -2703297867424869277L;

	protected HumanUserEntity() {
	}

	public HumanUserEntity(String userEmail, String userId, String firstName, String lastName, String encryptedPassword,
			Boolean emailVerificationStatus, long phoneNumber, String address) {
		super(userEmail, userId, firstName, lastName, encryptedPassword, emailVerificationStatus, address);
		
	}

}
