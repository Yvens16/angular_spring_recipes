package com.yvens_belaston_recipes.spring_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.yvens_belaston_recipes.spring_app.entity.User;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users2")
public class HController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public EntityModel<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(savedUser.getId()).withSelfRel();
        return EntityModel.of(savedUser, selfLink);
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .map(user -> EntityModel.of(user,
                        WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withRel("user")))
                .collect(Collectors.toList());

        Link link = WebMvcLinkBuilder.linkTo(UserController.class).withSelfRel();
        return CollectionModel.of(users, link);
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).get();

        Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(id).withSelfRel();
        Link allUsersLink = WebMvcLinkBuilder.linkTo(UserController.class).withRel("all-users");

        return EntityModel.of(user, selfLink, allUsersLink);
    }

    @PutMapping("/{id}")
    public EntityModel<User> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    return userRepository.save(user);
                }).get();

        Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(id).withSelfRel();

        return EntityModel.of(updatedUser, selfLink);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}