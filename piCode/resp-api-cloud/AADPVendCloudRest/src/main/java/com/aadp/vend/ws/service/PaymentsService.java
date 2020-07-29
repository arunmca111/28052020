package com.aadp.vend.ws.service;

import com.aadp.vend.ws.shared.dto.PaymentsInfoDto;

public interface PaymentsService  {
	
	PaymentsInfoDto fetchPaymentDtlByUsrId(String userId) throws Exception;

}
