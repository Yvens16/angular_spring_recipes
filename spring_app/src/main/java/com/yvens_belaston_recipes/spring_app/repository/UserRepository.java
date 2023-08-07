package com.yvens_belaston_recipes.spring_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvens_belaston_recipes.spring_app.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String string);
  Optional<UserEntity> findByEmail(String string);
}