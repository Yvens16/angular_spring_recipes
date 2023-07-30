package com.yvens_belaston_recipes.spring_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.entity.Address;
import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.AddressRepository;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@SpringBootApplication
public class SpringAppApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			User yvensBestTutor = new User();
			yvensBestTutor.setUsername("yvens");
			Address address = new Address("44 rue Alphonse Penaud", "Paris", "75020");
			yvensBestTutor.setShippingAddress(address);
			userRepository.save(yvensBestTutor);

			User marcos = new User();
			marcos.setUsername("Marcos");
			Address newAddress = new Address("Alto da Boa Vista", "Rio de Janeiro", "20531-590");
			marcos.setShippingAddress(newAddress);
			userRepository.save(marcos);
		};
	}
}
