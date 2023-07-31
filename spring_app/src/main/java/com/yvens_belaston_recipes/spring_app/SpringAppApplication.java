package com.yvens_belaston_recipes.spring_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yvens_belaston_recipes.spring_app.entity.Boat;
import com.yvens_belaston_recipes.spring_app.entity.Car;
import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.BoatRepository;
import com.yvens_belaston_recipes.spring_app.repository.CarRepository;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@SpringBootApplication
public class SpringAppApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarRepository roleRepository;

	@Autowired
	BoatRepository boatRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {
			// ######### Unidirectionelle seulement ###########
			System.out.println("######### Unidirectionelle seulement ###########");
			User yvensBestTutor = new User();
			yvensBestTutor.setUsername("yvens");
			Car ferrari = new Car("ferrari");
			Car porsche = new Car("porsche");
			yvensBestTutor.getCars().add(ferrari);
			yvensBestTutor.getCars().add(porsche);
			userRepository.save(yvensBestTutor);

			int index = 0;
			for (Car car : yvensBestTutor.getCars()) {
				System.out.println("Ma voiture numéro " + (index + 1) + " " + car.getModel());
				index += 1;
			}
			System.out.println("######### Unidirectionelle seulement ###########");

			// ######### Unidirectionelle seulement ###########

			// ######### Bidirectionelle seulement ###########
			System.out.println("######### Bidirectionelle seulement ###########");
			User yannickStudent = new User();
			yannickStudent.setUsername("Yannick");

			Boat blackPearl = new Boat("Black Pearl");
			Boat titanic = new Boat("Titanic");
			yannickStudent.getBoats().add(blackPearl);
			yannickStudent.getBoats().add(titanic);
			blackPearl.setUser(yannickStudent);
			titanic.setUser(yannickStudent);
			userRepository.save(yannickStudent);

			for (Boat boat : yannickStudent.getBoats()) {
				System.out.println("Mon bateau " + boat.getModel());
			}

			Boat boat = boatRepository.findById(1L).get();
			// System.out.println("@@@@@@@@@@" + boat.getModel());
			String username = boat.getUser().getUsername();
			System.out.println("Le propriétaire du bateau " + boat.getModel() + " est " + username);
			System.out.println("######### Bidirectionelle seulement ###########");

			// ######### Bidirectionelle seulement ###########
		};
	}
}
