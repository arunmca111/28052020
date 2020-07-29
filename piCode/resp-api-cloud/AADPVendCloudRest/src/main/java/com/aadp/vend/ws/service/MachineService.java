package com.aadp.vend.ws.service;

import java.util.List;

import com.aadp.vend.ws.shared.dto.MachineDtlDto;

public interface MachineService  {

	List<MachineDtlDto> getAllMachine() throws Exception;

}
