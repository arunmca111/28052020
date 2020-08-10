package com.aadpHome.solutions.ws.ui.contoller;

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

import com.aadpHome.solutions.ws.service.UserService;
import com.aadpHome.solutions.ws.shared.dto.UserDto;
import com.aadpHome.solutions.ws.ui.model.request.UserDetailsRequestModel;
import com.aadpHome.solutions.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id) {
		
		
		ModelMapper modelMapper = new ModelMapper();
	
		 UserDto userDto = userService.getUser(id);
		 return  modelMapper.map(userDto, UserRest.class);

		
	}

	@PostMapping
	@RequestMapping("/user")
	public UserRest createHumanUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
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
