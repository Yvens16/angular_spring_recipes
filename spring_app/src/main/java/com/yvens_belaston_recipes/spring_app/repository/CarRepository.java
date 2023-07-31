package com.yvens_belaston_recipes.spring_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvens_belaston_recipes.spring_app.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  

}
