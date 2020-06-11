package com.aadp.vend.ws.io.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.User;
import com.aadp.vend.ws.io.entity.HumanUser;
import com.aadp.vend.ws.io.entity.MachineUser;

@Repository
@Transactional
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public HumanUser findById(String emailId) {
		HumanUser humanUser = em.find(HumanUser.class, emailId);
		logger.info("HumanUser -> {}", humanUser);
		return humanUser;
	}
	
	public void addMachineForHuman(String emailId, List<MachineUser> machineusers) {		
		HumanUser humanUser = findById(emailId);
		logger.info("course.getReviews() -> {}", humanUser.getMachineUser());
		for(MachineUser machineuser:machineusers)
		{			
			//setting the relationship
			humanUser.addMachineUser(machineuser);
			machineuser.setHumanUser(humanUser);
			em.persist(machineuser);
		}
	}
	
	public void insert(User user) {
		em.persist(user);
	}

	public List<HumanUser> retrieveAllHumanUser() {
		return em.createQuery("select h from HumanUser h", HumanUser.class).getResultList();
	}

	public List<MachineUser> retrieveAllMachineUser() {
		return em.createQuery("select m from MachineUser m", MachineUser.class).getResultList();
	}
	
	

}