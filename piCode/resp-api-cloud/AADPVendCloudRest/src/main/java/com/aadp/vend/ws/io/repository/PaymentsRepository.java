package com.aadp.vend.ws.io.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.PaymentInfo;

@Repository
@Transactional
public class PaymentsRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public PaymentInfo fetchPaymentDtlByUsrId(String userId) {
		PaymentInfo payInfo = em.find(PaymentInfo.class, userId);
		logger.info("PaymentInfo By UsrId -> {}", payInfo);
		return payInfo;
	}

	

}