package com.yvens_belaston_recipes.spring_app.service;

import org.springframework.stereotype.Service;

import com.yvens_belaston_recipes.spring_app.dto.BoatDto;
import com.yvens_belaston_recipes.spring_app.entity.Boat;
import com.yvens_belaston_recipes.spring_app.entity.User;

@Service
public class BoatMapper {

  public BoatDto transformBoatEntityInBoatDto(Boat boat) {
    return new BoatDto(boat.getId(), boat.getModel(), boat.getUser().getUsername(), boat.getUser().getId());
  }


  public Boat transformBoatDtoInBoatEntity(BoatDto boatDto) {
    Boat boat = new Boat();
    User user = new User();
    user.setUsername(boatDto.getUsername());
    user.setId(boatDto.getUserId());

    boat.setId(boatDto.getId());
    boat.setModel(boatDto.getModel());
    boat.setUser(user);
    return boat;
  }

}
