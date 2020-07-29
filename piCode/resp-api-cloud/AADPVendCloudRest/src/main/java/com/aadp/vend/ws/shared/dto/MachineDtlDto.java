package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;

public class MachineDtlDto implements Serializable {

	private static final long serialVersionUID = 6697610674426838050L;
	private String machineCode;
	private String machineDescription;
	
	
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getMachineDescription() {
		return machineDescription;
	}
	public void setMachineDescription(String machineDescription) {
		this.machineDescription = machineDescription;
	}
	


}
