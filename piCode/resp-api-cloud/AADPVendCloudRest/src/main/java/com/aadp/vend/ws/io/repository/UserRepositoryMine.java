package com.aadp.vend.ws.io.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.Users;
import com.aadp.vend.ws.io.entity.HumanUserEntity;
import com.aadp.vend.ws.io.entity.Machine;

@Repository
@Transactional
public class UserRepositoryMine {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public HumanUserEntity findHumanUserByEmailId(String emailId) {
		HumanUserEntity humanUser = em.find(HumanUserEntity.class, emailId);
		logger.info("HumanUser -> {}", humanUser);
		return humanUser;
	}
	
	public Machine findMachineUserByEmailId(String emailId) {
		Machine machineUser = em.find(Machine.class, emailId);
		logger.info("MachineUser -> {}", machineUser);
		return machineUser;
	}
	
	public Users findUserByEmailId(String emailId) {
		Users users = em.find(Users.class, emailId);
		logger.info("User -> {}", users);
		return users;
	}

	public Machine addMachineUser( Machine machineuser) {

			em.persist(machineuser);
			em.flush();

			logger.info("Machine User Added -> {}", machineuser.getEmail());
			return machineuser;
	}

	public HumanUserEntity addHumanUser(HumanUserEntity humanUser) {
		em.persist(humanUser);
		em.flush();

		logger.info("Human User Added -> {}", humanUser.getEmail());
		logger.info("Human User Added -> {}", humanUser.getUserId());
		return humanUser;

	}

	public List<HumanUserEntity> retrieveAllHumanUser() {
		return em.createQuery("select h from HumanUser h", HumanUserEntity.class).getResultList();
	}

	public List<Machine> retrieveAllMachineUser() {
		return em.createQuery("select m from MachineUser m", Machine.class).getResultList();
	}

}