package com.yvens_belaston_recipes.spring_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main {

  @GetMapping("/")
  public String index() {
    return "hello";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }
}
