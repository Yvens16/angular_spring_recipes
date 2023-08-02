package com.yvens_belaston_recipes.spring_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvens_belaston_recipes.spring_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String string);
  Optional<List<User>> findByActive(boolean active);
  Optional<List<User>> findByLevelGreaterThan(int level);
}