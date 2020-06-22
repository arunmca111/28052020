package com.aadp.vend.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.aadp.vend.ws.shared.dto.HumanUserDto;
import com.aadp.vend.ws.shared.dto.MachineUserDto;
import com.aadp.vend.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto getUser(String email);
	
	HumanUserDto createHumanUser(HumanUserDto user);

	MachineUserDto createMachineUserForHuman(MachineUserDto user);

}
