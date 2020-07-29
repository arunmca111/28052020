package com.aadp.vend.ws.ui.contoller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadp.vend.ws.service.PaymentsService;
import com.aadp.vend.ws.shared.dto.PaymentsInfoDto;
import com.aadp.vend.ws.ui.model.response.PaymentsInfoResponse;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	PaymentsService PaymentsService;

	@GetMapping(path = "/{id}")
	public PaymentsInfoResponse fetchPaymentInfo(@PathVariable String id) {

		PaymentsInfoDto paymentsInfoDto = null;
		ModelMapper modelMapper = new ModelMapper();

		try {
			paymentsInfoDto = PaymentsService.fetchPaymentDtlByUsrId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelMapper.map(paymentsInfoDto, PaymentsInfoResponse.class);

	}

	
}
