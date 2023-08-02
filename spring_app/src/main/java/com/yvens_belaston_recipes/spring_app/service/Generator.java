package com.yvens_belaston_recipes.spring_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@Service
public class Generator {

  private UserRepository userRepository;

  public Generator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void generateUserList() {
    List<User> users = new ArrayList<>();
    users.add(new User("user1", true, 1));
    users.add(new User("user2", false, 2));
    users.add(new User("user3", true, 3));
    users.add(new User("user4", false, 4));
    users.add(new User("user5", true, 5));
    userRepository.saveAll(users);
  }
}
