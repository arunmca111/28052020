package com.aadpHome.solutions.ws.service;

import java.util.List;

import com.aadpHome.solutions.ws.shared.dto.DeviceDto;

public interface DeviceService  {

	DeviceDto createDevice(DeviceDto Device) throws Exception;
	List<DeviceDto> getDevice(String machineId) throws Exception;
	List<DeviceDto> getAllDevice() throws Exception;
	List<DeviceDto> getDeviceByuserID(String userId) throws Exception;
	List<DeviceDto> getactualDeviceByuserID(String userId) throws Exception;
	DeviceDto updateDevice(String deviceId, DeviceDto Device) throws Exception;
	void deleteDeviceUser(String deviceId) throws Exception;

}
