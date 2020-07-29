package com.aadp.vend.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadp.vend.ws.io.entity.PaymentInfo;
import com.aadp.vend.ws.io.entity.Products;
import com.aadp.vend.ws.io.repository.PaymentsRepository;
import com.aadp.vend.ws.io.repository.ProductsRepository;
import com.aadp.vend.ws.service.PaymentsService;
import com.aadp.vend.ws.service.ProductsService;
import com.aadp.vend.ws.shared.Utils;
import com.aadp.vend.ws.shared.dto.FavoriteDto;
import com.aadp.vend.ws.shared.dto.PaymentsInfoDto;
import com.aadp.vend.ws.shared.dto.ProductsDto;

@Service
public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	PaymentsRepository paymentsRepository;

	@Autowired
	Utils utils;

	@Override
	public PaymentsInfoDto fetchPaymentDtlByUsrId(String userId) throws Exception {
		PaymentInfo paymentInfoEntity = paymentsRepository.fetchPaymentDtlByUsrId(userId);
		
		String publicPaymentRef = utils.generatePaymentRef(11);
		

		if (paymentInfoEntity == null)
			throw new Exception(userId);

		ModelMapper modelMapper = new ModelMapper();
		PaymentsInfoDto returnValue = modelMapper.map(paymentInfoEntity, PaymentsInfoDto.class);
		returnValue.setTransactionRef(publicPaymentRef);
		return returnValue;

	}
	

}
