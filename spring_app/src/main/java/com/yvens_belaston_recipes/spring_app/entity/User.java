package com.yvens_belaston_recipes.spring_app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  /*
   * Sans le @manytoone de l'autre coté, cela nous fais une relation unidirectionnelle
   * On ne pourra donc pas récupérer l'utilisateur à partir de la voiture
   */
  @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private List<Car> cars = new ArrayList<>();


  @OneToMany(mappedBy = "user", cascade= CascadeType.PERSIST)
  private List<Boat> boats = new ArrayList<>();

  public User() {
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

  public List<Car> getCars() {
    return cars;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

  public List<Boat> getBoats() {
    return boats;
  }

  public void setBoats(List<Boat> boats) {
    this.boats = boats;
  }
}
