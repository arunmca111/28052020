package com.aadp.vend.ws;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aadp.vend.ws.security.AppProperties;

@SpringBootApplication
public class AadpVendCloudRestApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(AadpVendCloudRestApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppProperties()
	{
		return new AppProperties();
	}

	@Override
	public void run(String... args) throws Exception {

		
		
		/*
		 * userRepository.insert(new HumanUserEntity("arunmca111@gmail.com",
		 * "arunmca111", "arun", "asokan", "kjdejbb3", true, 9962240340L, "Mylapore"));
		 * 
		 * 
		 * List<MachineUser> machineUser = new ArrayList<>();
		 * 
		 * machineUser.add(new MachineUser("machineiot@aadp.com", "userId", "firstName",
		 * "lastName", "encryptedPassword", true, "address", "machineType", true, null,
		 * null, null, 565L));
		 * 
		 * machineUser.add(new MachineUser("machineiot111@aadp.com", "userId",
		 * "firstName", "lastName", "encryptedPassword", true, "address", "machineType",
		 * true, null, null, null, 565L));
		 * 
		 * userRepository.addMachineForHumanUser("arunmca111@gmail.com", machineUser);
		 */
		 

	}

}
