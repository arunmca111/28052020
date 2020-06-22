package com.aadp.vend.ws.ui.contoller;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadp.vend.ws.service.UserService;
import com.aadp.vend.ws.shared.dto.HumanUserDto;
import com.aadp.vend.ws.shared.dto.MachineUserDto;
import com.aadp.vend.ws.shared.dto.UserDto;
import com.aadp.vend.ws.ui.model.request.HumanUserDetailsRequestModel;
import com.aadp.vend.ws.ui.model.request.MachineUserDetailsRequestModel;
import com.aadp.vend.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class HumanUserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();
		ModelMapper modelMapper = new ModelMapper();
	
		 UserDto userDto = userService.getUser(id);
		 return returnValue = modelMapper.map(userDto, UserRest.class);

		
	}

	@PostMapping
	@RequestMapping("/human")
	public UserRest createHumanUser(@RequestBody HumanUserDetailsRequestModel humanUserDetails) {
		UserRest returnValue = new UserRest();

		ModelMapper modelMapper = new ModelMapper();
		HumanUserDto userDto = modelMapper.map(humanUserDetails, HumanUserDto.class);

		HumanUserDto createdUser = userService.createHumanUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);

		return returnValue;
	}

	@PostMapping
	@RequestMapping("/machine")
	public UserRest createMachineUserForHuman(@RequestBody MachineUserDetailsRequestModel machineUserDetails) {
		
		UserRest returnValue = new UserRest();
		ModelMapper modelMapper = new ModelMapper();
		MachineUserDto userDto = modelMapper.map(machineUserDetails, MachineUserDto.class);

		MachineUserDto createdUser = userService.createMachineUserForHuman(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);

		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "update user called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}

}
