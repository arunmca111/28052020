package com.aadp.vend.ws.io.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.CartItems;
import com.aadp.vend.ws.io.entity.Orders;

@Repository
@Transactional
public class OrdersRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Orders addOrders(Orders orders) {

		logger.info("adding Orders");
		
		em.persist(orders);
		em.flush();

		logger.info("Orders Added -> {}", orders.getOrdersId());
		return orders;

	}
	
	public List<Orders> fetchAllOrders() {
		return em.createQuery("select o from Orders o", Orders.class).getResultList();
	}

	public Orders findOrdersById(String ordersId) {
		Orders orders = em.find(Orders.class, ordersId);
		logger.info("Orders By Id -> {}", orders);
		return orders;
	}

}