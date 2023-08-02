package com.yvens_belaston_recipes.spring_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yvens_belaston_recipes.spring_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String string);

  Optional<List<User>> findByActive(boolean active);

  Optional<List<User>> findByLevelGreaterThan(int level);

  @Query("select u from User u where u.level = :level and u.active = :active")
  Optional<List<User>> findByLevelAndActive(@Param("level") int level,
      @Param("active") boolean active);
}