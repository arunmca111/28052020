package com.aadp.vend.ws.ui.contoller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadp.vend.ws.service.MachineService;
import com.aadp.vend.ws.shared.dto.MachineDtlDto;
import com.aadp.vend.ws.ui.model.response.MachineDtlResponse;

@RestController
@RequestMapping("/machine")
public class MachineController {

	@Autowired
	MachineService machineService;

	@GetMapping()
	public List<MachineDtlResponse> getAllMachine() {

		List<MachineDtlDto> machinesDtlDto = null;
		List<MachineDtlResponse> returnValue = new ArrayList<MachineDtlResponse>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			machinesDtlDto = machineService.getAllMachine();

			for (MachineDtlDto machineDtlDto : machinesDtlDto) {
				MachineDtlResponse machineDtlModel = new MachineDtlResponse();
				modelMapper.map(machineDtlDto, machineDtlModel);
				returnValue.add(machineDtlModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;

	}

}
