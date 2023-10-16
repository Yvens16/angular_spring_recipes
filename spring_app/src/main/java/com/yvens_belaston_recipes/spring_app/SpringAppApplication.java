package com.yvens_belaston_recipes.spring_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@SpringBootApplication
public class SpringAppApplication {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			User yvensBestTutor = new User();
			yvensBestTutor.setUsername("yvens");
			userRepository.save(yvensBestTutor);

			User marcos = new User();
			marcos.setUsername("Marcos");
			userRepository.save(marcos);
		};
	}
}
