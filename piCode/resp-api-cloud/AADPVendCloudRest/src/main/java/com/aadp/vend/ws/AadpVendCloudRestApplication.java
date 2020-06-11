package com.aadp.vend.ws;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aadp.vend.ws.io.entity.HumanUser;
import com.aadp.vend.ws.io.entity.MachineUser;
import com.aadp.vend.ws.io.repository.UserRepository;

@SpringBootApplication
public class AadpVendCloudRestApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AadpVendCloudRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		
		  userRepository.insert(new HumanUser("arunmca111@gmail.com", "arunmca111",
		  "arun", "asokan", "kjdejbb3", true, 9962240340L, "Mylapore"));
		  
		  
		  List<MachineUser> machineUser = new ArrayList<>();
		  
		  machineUser.add(new MachineUser("machineiot@aadp.com", "userId", "firstName",
		  "lastName", "encryptedPassword", true, "address", "machineType", true, null,
		  null, null, 565L));
		  
		  userRepository.addMachineForHuman("arunmca111@gmail.com", machineUser);
		 

	}

}
