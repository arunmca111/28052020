package com.aadpHome.solutions.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;


import com.aadpHome.solutions.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto getUser(String email);
	
	UserDto createUser(UserDto user);



}
