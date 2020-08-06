package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class MachineInventory implements Serializable {

	private static final long serialVersionUID = 9109597633425631167L;


	protected MachineInventory() {
	}

	public MachineInventory(String machineCode, String machineSlotId, boolean machineSlotActiveStatus, String productId,
			LocalDateTime productExpiryDate, int machineItemCount, LocalDateTime machineLastUpdatedDate, LocalDateTime machineCreatedDate) {
		
		this.machineCode = machineCode;
		this.machineSlotId = machineSlotId;
		this.machineSlotActiveStatus = machineSlotActiveStatus;
		this.productId = productId;
		this.productExpiryDate = productExpiryDate;
		this.machineCode = machineCode;
		this.machineItemCount = machineItemCount;
		this.machineLastUpdatedDate = machineLastUpdatedDate;
		this.machineCreatedDate = machineCreatedDate;
 	}
	
	@Id
	@Column(nullable = false, length = 7)
    private String machineCode;
	
	@Id
	@Column(nullable = false, length = 4)
    private String machineSlotId;
	
	@Id
	@Column(nullable = false, length = 120)
	private String productId;
	
	@Column(nullable = false)
    private boolean machineSlotActiveStatus;

	
	
	@Column(nullable = false)
	private LocalDateTime productExpiryDate;
	
	@Column(nullable = false, length = 120)
	private int machineItemCount;

	@UpdateTimestamp
	private LocalDateTime machineLastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime machineCreatedDate;


	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineSlotId() {
		return machineSlotId;
	}

	public void setMachineSlotId(String machineSlotId) {
		this.machineSlotId = machineSlotId;
	}

	public boolean isMachineSlotActiveStatus() {
		return machineSlotActiveStatus;
	}

	public void setMachineSlotActiveStatus(boolean machineSlotActiveStatus) {
		this.machineSlotActiveStatus = machineSlotActiveStatus;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public LocalDateTime getProductExpiryDate() {
		return productExpiryDate;
	}

	public void setProductExpiryDate(LocalDateTime productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}

	public int getMachineItemCount() {
		return machineItemCount;
	}

	public void setMachineItemCount(int machineItemCount) {
		this.machineItemCount = machineItemCount;
	}

	public LocalDateTime getMachineLastUpdatedDate() {
		return machineLastUpdatedDate;
	}

	public void setMachineLastUpdatedDate(LocalDateTime machineLastUpdatedDate) {
		this.machineLastUpdatedDate = machineLastUpdatedDate;
	}

	public LocalDateTime getMachineCreatedDate() {
		return machineCreatedDate;
	}

	public void setMachineCreatedDate(LocalDateTime machineCreatedDate) {
		this.machineCreatedDate = machineCreatedDate;
	}


}
