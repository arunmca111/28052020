package com.aadpHome.solutions.ws.ui.contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadpHome.solutions.ws.iot.IotMqttConnect;
import com.aadpHome.solutions.ws.service.DeviceService;
import com.aadpHome.solutions.ws.shared.dto.DeviceDto;
import com.aadpHome.solutions.ws.ui.model.request.DeviceDetailRequestModel;
import com.aadpHome.solutions.ws.ui.model.response.OperationStatusModel;
import com.aadpHome.solutions.ws.ui.model.response.ActualDeviceResponse;
import com.aadpHome.solutions.ws.ui.model.response.DeviceResponse;
import com.aadpHome.solutions.ws.ui.model.response.RequestOperationStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	DeviceService DeviceService;

	@GetMapping(path = "/{machineId}")
	public List<DeviceResponse> getDeviceForMachine(@PathVariable String machineId) {
		List<DeviceResponse> returnValue = new ArrayList<DeviceResponse>();
		List<DeviceDto> DeviceDto = null;
		ModelMapper modelMapper = new ModelMapper();

		try {
			DeviceDto = DeviceService.getDevice(machineId);

			for (DeviceDto productDto : DeviceDto) {
				DeviceResponse productModel = new DeviceResponse();
				modelMapper.map(productDto, productModel);
				returnValue.add(productModel);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@PatchMapping(path = "userId/{userId}/deviceId/{deviceId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public DeviceResponse updateUser(@PathVariable String userId, @PathVariable String deviceId , @RequestBody DeviceDetailRequestModel productDetails) {
		ModelMapper modelMapper = new ModelMapper();
		DeviceResponse returnValue = new DeviceResponse();
		DeviceDto DeviceDto = modelMapper.map(productDetails, DeviceDto.class);
		DeviceDto updateProductDto = null;

		try {
			updateProductDto = DeviceService.updateDevice(deviceId, DeviceDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateDeviceMqtt(userId);
		
		returnValue = new ModelMapper().map(updateProductDto, DeviceResponse.class);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteDevice(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationStatus.ERROR.name());

		try {
			DeviceService.deleteDeviceUser(id);
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}

	@GetMapping()
	public List<DeviceResponse> getAllDevice() {

		List<DeviceDto> DeviceDto = null;
		List<DeviceResponse> returnValue = new ArrayList<DeviceResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			DeviceDto = DeviceService.getAllDevice();

			for (DeviceDto productDto : DeviceDto) {
				DeviceResponse productModel = new DeviceResponse();
				modelMapper.map(productDto, productModel);
				returnValue.add(productModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@GetMapping(path = "/{userId}/userDevice", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public List<DeviceResponse> getDeviceByuserID(@PathVariable String userId) {

		List<DeviceDto> DeviceDto = null;
		List<DeviceResponse> returnValue = new ArrayList<DeviceResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			DeviceDto = DeviceService.getDeviceByuserID(userId);

			for (DeviceDto deviceDto : DeviceDto) {
				DeviceResponse deviceModel = new DeviceResponse();
				modelMapper.map(deviceDto, deviceModel);
				returnValue.add(deviceModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}
	
	@GetMapping(path = "/{userId}/actualDevice", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public List<ActualDeviceResponse> getActualDeviceByuserID(@PathVariable String userId) {

		List<DeviceDto> DeviceDto = null;
		List<ActualDeviceResponse> returnValue = new ArrayList<ActualDeviceResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			DeviceDto = DeviceService.getactualDeviceByuserID(userId);

			for (DeviceDto deviceDto : DeviceDto) {
				ActualDeviceResponse deviceModel = new ActualDeviceResponse();
				modelMapper.map(deviceDto, deviceModel);
				returnValue.add(deviceModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

	@PostMapping
	@RequestMapping("/newdevice")
	public DeviceResponse createDevice(@RequestBody DeviceDetailRequestModel deviceDetails) {
		DeviceResponse returnValue = new DeviceResponse();
		DeviceDto createdDevice = null;
		ModelMapper modelMapper = new ModelMapper();
		DeviceDto DeviceDto = modelMapper.map(deviceDetails, DeviceDto.class);

		try {
			createdDevice = DeviceService.createDevice(DeviceDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		returnValue = modelMapper.map(createdDevice, DeviceResponse.class);

		return returnValue;
	}

	@PatchMapping
	public String updateDeviceByPut() {
		return "update patch Device called";
	}

	
	private void updateDeviceMqtt(String userId) {
		List<DeviceDto> DeviceDto = null;
		List<ActualDeviceResponse> returnValue = new ArrayList<ActualDeviceResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			DeviceDto = DeviceService.getactualDeviceByuserID(userId);

			for (DeviceDto deviceDto : DeviceDto) {
				ActualDeviceResponse deviceModel = new ActualDeviceResponse();
				modelMapper.map(deviceDto, deviceModel);
				returnValue.add(deviceModel);
			}
			
			// Creating Object of ObjectMapper define in Jakson Api 
	        ObjectMapper Obj = new ObjectMapper(); 
	        String jsonStr ="";
	  
	        try { 
	  
	             jsonStr = Obj.writeValueAsString(returnValue); 
	  
	            // Displaying JSON String 
	            System.out.println(jsonStr); 
	            
	            new IotMqttConnect().sendMessage(jsonStr);
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
