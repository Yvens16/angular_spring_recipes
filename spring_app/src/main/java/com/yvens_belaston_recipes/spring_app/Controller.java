package com.yvens_belaston_recipes.spring_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  EnvProperties envProperties; 

  public Controller(EnvProperties envProperties) {
    this.envProperties = envProperties;
  }

  @GetMapping("/getData")
  public String getData() {
    return "Hello World: Ici " + envProperties.getVariable() ;
  }
}
