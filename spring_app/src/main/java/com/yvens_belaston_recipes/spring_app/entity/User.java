package com.yvens_belaston_recipes.spring_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private boolean active;
  private int level;

  /*
   * Sans le @manytoone de l'autre coté, cela nous fais une relation
   * unidirectionnelle
   * On ne pourra donc pas récupérer l'utilisateur à partir de la voiture
   */

  public User() {
  }

  public User(String username, boolean active, int level) {
    this.username = username;
    this.active = active;
    this.level = level;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
