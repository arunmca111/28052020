package com.aadp.vend.ws.io.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aadp.vend.ws.io.entity.Machine;

@Repository
@Transactional
public class MachineRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;



	public List<Machine> getAllMachine() {
		return em.createQuery("select m from Machine m where isActive = true", Machine.class).getResultList();
	}
	
	 


}