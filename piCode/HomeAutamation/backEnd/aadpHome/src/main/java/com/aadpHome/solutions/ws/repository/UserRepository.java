package com.aadpHome.solutions.ws.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadpHome.solutions.ws.io.entity.Users;

@Repository
@Transactional
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	
	public Users findUserByEmailId(String emailId) {
		Users users = em.find(Users.class, emailId);
		logger.info("User -> {}", users);
		return users;
	}

	public Users addUser(Users user) {
		em.persist(user);
		em.flush();

		logger.info("User Added -> {}", user.getEmail());
		logger.info("User Added -> {}", user.getUserId());
		return user;

	}

}