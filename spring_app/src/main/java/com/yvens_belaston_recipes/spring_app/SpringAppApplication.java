package com.yvens_belaston_recipes.spring_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.entity.Role;
import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.RoleRepository;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@SpringBootApplication
public class SpringAppApplication {
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {

			Role normal_user_role = new Role();
			normal_user_role.setTitle("ROLE_USER");

			Role admin_role = new Role();
			admin_role.setTitle("ROLE_ADMIN");

			normal_user_role = roleRepository.save(normal_user_role);
			admin_role = roleRepository.save(admin_role);


			User user = new User();
			user.setUsername("user");

			User admin = new User();
			admin.setUsername("admin");

			user.getRoles().add(normal_user_role);
			normal_user_role.getUsers().add(user);

			
			admin.getRoles().add(normal_user_role);
			normal_user_role.getUsers().add(admin);

			admin.getRoles().add(admin_role);
			admin_role.getUsers().add(admin);
			
			userRepository.save(user);
			userRepository.save(admin);
		};
	}
}
