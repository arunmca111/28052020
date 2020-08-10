package com.aadpHome.solutions.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aadpHome.solutions.ws.exceptions.UserServiceException;


import com.aadpHome.solutions.ws.io.entity.Users;
import com.aadpHome.solutions.ws.repository.UserRepository;
import com.aadpHome.solutions.ws.service.UserService;
import com.aadpHome.solutions.ws.shared.Utils;
import com.aadpHome.solutions.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto user) {

		if (userRepository.findUserByEmailId(user.getEmail()) != null)
			throw new UserServiceException("Record already exists");

		ModelMapper modelMapper = new ModelMapper();
		Users users = modelMapper.map(user, Users.class);

		String publicUserId = utils.generateUserId(30);
		users.setUserId(publicUserId);
		users.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Users storedUserDetails = userRepository.addUser(users);

		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users userEntity = userRepository.findUserByEmailId(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), 
				userEntity.getEmailVerificationStatus(),
				true, true,
				true, new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		Users userEntity = userRepository.findUserByEmailId(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

		return returnValue;
	}

}
