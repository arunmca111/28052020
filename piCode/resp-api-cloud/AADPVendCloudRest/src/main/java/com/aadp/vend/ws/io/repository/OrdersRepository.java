package com.aadp.vend.ws.io.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.Orders;
import com.aadp.vend.ws.io.entity.Products;

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

	public List<Orders> fetchAllOrders(String userId) {

		TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.userId = :userId", Orders.class);
		return query.setParameter("userId", userId).getResultList();

	}

	public Orders findOrdersById(String ordersId) {
		Orders orders = em.find(Orders.class, ordersId);
		logger.info("Orders By Id -> {}", orders);
		return orders;
	}

	public Orders findOrdersById(String ordersId, String userId) {
		TypedQuery<Orders> query = em
				.createQuery("SELECT o FROM Orders o WHERE o.ordersId=:ordersId and o.userId = :userId", Orders.class);
		query.setParameter("ordersId", ordersId);
		return query.setParameter("userId", userId).getResultList().stream().findFirst().orElse(null);
	}

}