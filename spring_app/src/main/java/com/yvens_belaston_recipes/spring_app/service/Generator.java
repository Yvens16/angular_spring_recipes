package com.yvens_belaston_recipes.spring_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yvens_belaston_recipes.spring_app.entity.Role;
import com.yvens_belaston_recipes.spring_app.entity.UserEntity;
import com.yvens_belaston_recipes.spring_app.repository.RoleRepository;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@Service
public class Generator {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private BCryptPasswordEncoder bcryptEncoder;

  public Generator(UserRepository userRepository, RoleRepository roleRepository,
      BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bcryptEncoder = passwordEncoder;
  }

  public void generateUserList() {
    Role admin = roleRepository.findById(1L).get();
    Role user = roleRepository.findById(2L).get();

    List<UserEntity> users = new ArrayList<>();
    users.add(new UserEntity("user1", bcryptEncoder.encode("password"), true, 1, user, "email@gmail.com"));
    users.add(new UserEntity("user2", bcryptEncoder.encode("password"), false, 2, user, "email@gmail.com"));
    users.add(new UserEntity("user3", bcryptEncoder.encode("password"), true, 3, user, "email@gmail.com"));
    users.add(new UserEntity("user4", bcryptEncoder.encode("password"), false, 4, user, "email@gmail.com"));
    users.add(new UserEntity("user5", bcryptEncoder.encode("password"), true, 5, admin, "email@gmail.com"));
    users.add(new UserEntity("user6", bcryptEncoder.encode("password"), false, 5, admin, "email@gmail.com"));
    users.add(new UserEntity("user7", bcryptEncoder.encode("password"), true, 5, admin, "email@gmail.com"));

    userRepository.saveAll(users);
  }

  public void generateRoles() {
    List<Role> roles = new ArrayList<>();
    roles.add(new Role("ADMIN"));
    roles.add(new Role("USER"));
    roleRepository.saveAll(roles);
  }
}
