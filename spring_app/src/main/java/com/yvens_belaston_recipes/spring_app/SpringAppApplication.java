package com.yvens_belaston_recipes.spring_app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.entity.User;
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
			generatorService.generateUserList();

			User user1 = userRepository.findByUsername("user1").get();
			System.out.println("@@@@@@@@ user1 " + user1.getUsername());

			Optional<List<User>> userActives = userRepository.findByActive(true);
			userActives.get().stream().map(User::getUsername)
					.forEach((name) -> System.out.println("nom du user actif:  " + name));

			Optional<List<User>> userNonActives = userRepository.findByActive(false);
			userNonActives.get().stream().map(User::getUsername)
					.forEach((name) -> System.out.println("nom du user Non actif:  " + name));

			Optional<List<User>> userLevelGreaterThan3 = userRepository.findByLevelGreaterThan(3);
			userLevelGreaterThan3.get().stream().forEach((user) -> System.out
					.println("nom du user avec un niveau supérieur à 3:  " + user.getUsername() + " level: " + user.getLevel()));
		};
	}
}
