package com.yvens_belaston_recipes.spring_app.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yvens_belaston_recipes.spring_app.dto.UserDto;
import com.yvens_belaston_recipes.spring_app.jwt.JwtUtil;
import com.yvens_belaston_recipes.spring_app.service.UserService;
import com.yvens_belaston_recipes.spring_app.utility.ApiResponse;

@Controller
public class UserController {

  private UserService userService;
  private JwtUtil jwtUtilService;

  public UserController(UserService userService, JwtUtil jwtUtilService) {
    this.userService = userService;
    this.jwtUtilService = jwtUtilService;
  }

  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> register(@RequestBody UserDto user) {
    HashMap<String, Object> data = new HashMap<>();
    try {

      userService.register(user);
      String token = jwtUtilService.generateToken(user);
      data.put("user", user);
      data.put("token", token);
      return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> login(@RequestBody UserDto user) {
    HashMap<String, Object> data = new HashMap<>();
    try {
      userService.login(user);
      String token = jwtUtilService.generateToken(user);
      data.put("user", user);
      data.put("token", token);
      return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/only-admin-data")
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> onlyAdminData() {
    HashMap<String, Object> data = new HashMap<>();
    try {
      data.put("message", "This is only for admin");
      return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/user-admin-data")
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> userAdminData() {
    HashMap<String, Object> data = new HashMap<>();
    try {
      data.put("message", "This is for user and admin");
      return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/permit")
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> permit() {
    HashMap<String, Object> data = new HashMap<>();
    try {
      data.put("message", "This is for all");
      return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}
