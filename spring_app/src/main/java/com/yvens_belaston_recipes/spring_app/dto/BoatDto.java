package com.yvens_belaston_recipes.spring_app.dto;

public class BoatDto {

  private Long id;
  private String model;
  private String username;
  private Long userId;

  public BoatDto(Long id, String model, String username, Long userId) {
    this.id = id;
    this.model = model;
    this.username = username;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }


  public String getModel() {
    return model;
  }

  public String getUsername() {
    return username;
  }

  public Long getUserId() {
    return userId;
  }
}
