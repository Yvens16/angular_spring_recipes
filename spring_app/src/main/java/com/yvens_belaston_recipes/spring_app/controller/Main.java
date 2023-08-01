package com.yvens_belaston_recipes.spring_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yvens_belaston_recipes.spring_app.dto.BoatDto;
import com.yvens_belaston_recipes.spring_app.dto.UserDto;
import com.yvens_belaston_recipes.spring_app.entity.Boat;
import com.yvens_belaston_recipes.spring_app.entity.Car;
import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.BoatRepository;
import com.yvens_belaston_recipes.spring_app.repository.CarRepository;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@Controller
public class Main {

  private BoatRepository boatRepository;
  private CarRepository carRepository;
  private UserRepository userRepository;

  public Main(BoatRepository boatRepository, CarRepository carRepository, UserRepository userRepository) {
    this.boatRepository = boatRepository;
    this.carRepository = carRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/cars") // Pas de problème car one-to-many unidirectionelle (pas de référence à Car dans
                       // User)
  @ResponseBody()
  public List<Car> getCars() {
    return carRepository.findAll();
  }

  @GetMapping("/boats") // Problème car one-to-many bidirectionelle (référence à Boat dans User)
  @ResponseBody()
  public List<Boat> getBoats() {
    return boatRepository.findAll();
  }

  @GetMapping("/users") // Problème car one-to-many bidirectionelle (référence à User dans Boat)
  @ResponseBody()
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  // ############# SOLUTIONS #############
  @GetMapping("/boats-solution")
  @ResponseBody()
  public List<BoatDto> getBoatsSolution() {
    List<Boat> boats = boatRepository.findAll();
    List<BoatDto> boatsDto = new ArrayList<>();
    for (Boat boat : boats) {
      boatsDto.add(new BoatDto(boat.getId(), boat.getModel(), boat.getUser().getUsername(), boat.getUser().getId()));
    }
    return boatsDto;
  }

  @GetMapping("/users-solution")
  @ResponseBody()
  public List<UserDto> getUsersSolution() {
    List<User> users = userRepository.findAll();
    List<UserDto> usersDto = new ArrayList<>();
    for (User user : users) {
      usersDto.add(new UserDto(user.getId(), user.getUsername()));
    }
    return usersDto;
  }

}
