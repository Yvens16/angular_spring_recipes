package com.yvens_belaston_recipes.spring_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.repository.UserRepository;
import com.yvens_belaston_recipes.spring_app.service.Generator;

@SpringBootApplication
public class SpringAppApplication {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Generator generatorService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			generatorService.generateRoles();
			generatorService.generateUserList();
		};

	}
}
