package com.aadpHome.solutions.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadpHome.solutions.ws.io.entity.Device;
import com.aadpHome.solutions.ws.repository.DeviceRepository;
import com.aadpHome.solutions.ws.service.DeviceService;
import com.aadpHome.solutions.ws.shared.Utils;
import com.aadpHome.solutions.ws.shared.dto.DeviceDto;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository DeviceRepository;

	@Autowired
	Utils utils;

	@Override
	public List<DeviceDto> getDevice(String machineId) throws Exception {
		List<DeviceDto> returnValue = new ArrayList<>();
		DeviceDto storeDto = null;
		List<Object[]> results = DeviceRepository.findDeviceBymachineId(machineId);
		
		for (Object[] device : results) {
			storeDto = new DeviceDto();
			
			storeDto.setDeviceId((String) device[1]);
			storeDto.setTitle((String) device[2]);
			storeDto.setDescription((String) device[3]);
			storeDto.setOnOffState((int) device[4]);
			storeDto.setImageUrl((String) device[5]);
			storeDto.setFavorite((boolean) device[6]);
			returnValue.add(storeDto);
			
		}

		return returnValue;

	}
	
	@Override
	public List<DeviceDto> getAllDevice() throws Exception {
		List<DeviceDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Device> devices = DeviceRepository.findAllDevice();

		for (Device device : devices) {
			returnValue.add(modelMapper.map(device, DeviceDto.class));
		}

		return returnValue;
	}
	
	
	@Override
	public List<DeviceDto> getactualDeviceByuserID(String userId) throws Exception {
		List<DeviceDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Device> devices = DeviceRepository.getactualDeviceByuserID(userId);

		for (Device device : devices) {
			returnValue.add(modelMapper.map(device, DeviceDto.class));
		}

		return returnValue;
	}
	
	@Override
	public List<DeviceDto> getDeviceByuserID(String userId) throws Exception {
		List<DeviceDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Device> devices = DeviceRepository.getDeviceByuserID(userId);

		for (Device device : devices) {
			returnValue.add(modelMapper.map(device, DeviceDto.class));
		}

		return returnValue;
	}

	@Override
	public DeviceDto createDevice(DeviceDto Device) throws Exception {
		if (DeviceRepository.findDeviceById(Device.getDeviceId()) != null)
			throw new Exception("Device already exists");

		ModelMapper modelMapper = new ModelMapper();
		Device deviceEntity = modelMapper.map(Device, Device.class);

		String publicDeviceId = utils.generateDeviceId(30);
		deviceEntity.setDeviceId(publicDeviceId);

		Device storedUserDetails = DeviceRepository.addDevice(deviceEntity);

		DeviceDto returnValue = modelMapper.map(storedUserDetails, DeviceDto.class);

		return returnValue;

	}

	@Override
	public DeviceDto updateDevice(String deviceId, DeviceDto Device) throws Exception {

		Device deviceEntity = DeviceRepository.findDeviceById(deviceId);

		if (deviceEntity == null) {
			throw new Exception(deviceId);
		}
		if (Device.getDescription() != null) {
			deviceEntity.setDescription(Device.getDescription());
		}
		if (Device.getImageUrl() != null) {
			deviceEntity.setImageUrl(Device.getImageUrl());
		}
	
		deviceEntity.setOnOffState(Device.getOnOffState());
		

		if (Device.getTitle() != null) {
			deviceEntity.setTitle(Device.getTitle());
		}

		deviceEntity.setFavorite(Device.isFavorite());

		DeviceRepository.updateDevice(deviceEntity);

		ModelMapper modelMapper = new ModelMapper();
		DeviceDto returnValue = modelMapper.map(deviceEntity, DeviceDto.class);

		return returnValue;
	}

	@Override
	public void deleteDeviceUser(String deviceId) throws Exception {
		Device DeviceEntity = DeviceRepository.findDeviceById(deviceId);

		if (DeviceEntity == null)
			throw new Exception(deviceId);

		DeviceRepository.deleteDevice(DeviceEntity);

	}

}
