package com.aadp.vend.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aadp.vend.ws.exceptions.UserServiceException;
import com.aadp.vend.ws.io.entity.HumanUserEntity;
import com.aadp.vend.ws.io.entity.MachineUserEnitity;
import com.aadp.vend.ws.io.entity.Users;
import com.aadp.vend.ws.io.repository.UserRepositoryMine;
import com.aadp.vend.ws.service.UserService;
import com.aadp.vend.ws.shared.Utils;
import com.aadp.vend.ws.shared.dto.HumanUserDto;
import com.aadp.vend.ws.shared.dto.MachineUserDto;
import com.aadp.vend.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositoryMine userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Override
	public HumanUserDto createHumanUser(HumanUserDto user) {

		if (userRepository.findHumanUserByEmailId(user.getEmail()) != null)
			throw new UserServiceException("Record already exists");

		ModelMapper modelMapper = new ModelMapper();
		HumanUserEntity humanUserEntity = modelMapper.map(user, HumanUserEntity.class);

		String publicUserId = utils.generateUserId(30);
		humanUserEntity.setUserId(publicUserId);
		humanUserEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		HumanUserEntity storedUserDetails = userRepository.addHumanUser(humanUserEntity);

		HumanUserDto returnValue = modelMapper.map(storedUserDetails, HumanUserDto.class);

		return returnValue;
	}

	@Override
	public MachineUserDto createMachineUserForHuman(MachineUserDto user) {

		if (userRepository.findHumanUserByEmailId(user.getHumanUserEmail()) == null)
			throw new UserServiceException("No Human User available for the requestested machine");
		
		if (userRepository.findMachineUserByEmailId(user.getEmail()) != null)
			throw new UserServiceException("Machine already exists by the email id, Try some other Mail Id");

		ModelMapper modelMapper = new ModelMapper();
		MachineUserEnitity machineUserEnitity = modelMapper.map(user, MachineUserEnitity.class);
		List<MachineUserEnitity> machineUserList = new ArrayList<MachineUserEnitity>();

		String publicUserId = utils.generateUserId(30);
		machineUserEnitity.setUserId(publicUserId);
		machineUserEnitity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		machineUserList.add(machineUserEnitity);
		MachineUserEnitity storedUserDetails = userRepository.addMachineForHumanUser(user.getHumanUserEmail(),
				machineUserList);

		MachineUserDto returnValue = modelMapper.map(storedUserDetails, MachineUserDto.class);

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
