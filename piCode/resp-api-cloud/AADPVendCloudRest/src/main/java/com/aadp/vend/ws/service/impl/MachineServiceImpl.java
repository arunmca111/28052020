package com.aadp.vend.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadp.vend.ws.io.entity.Machine;
import com.aadp.vend.ws.io.entity.Products;
import com.aadp.vend.ws.io.repository.MachineRepository;
import com.aadp.vend.ws.io.repository.ProductsRepository;
import com.aadp.vend.ws.service.MachineService;
import com.aadp.vend.ws.shared.Utils;
import com.aadp.vend.ws.shared.dto.MachineDtlDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	MachineRepository machineRepository;

	@Autowired
	Utils utils;

	
	@Override
	public List<MachineDtlDto> getAllMachine() throws Exception {
		List<MachineDtlDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Iterable<Machine> machines = machineRepository.getAllMachine();

		for (Machine machine : machines) {
			returnValue.add(modelMapper.map(machine, MachineDtlDto.class));
		}

		return returnValue;
	}
	
}
