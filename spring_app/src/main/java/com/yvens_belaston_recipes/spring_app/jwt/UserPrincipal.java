package com.yvens_belaston_recipes.spring_app.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yvens_belaston_recipes.spring_app.entity.UserEntity;

public class UserPrincipal implements UserDetails {

  UserEntity userEntity;

  public UserPrincipal(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getType()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    // Pourrait aussi être un email ou un autre identifiant unique de l'utilisatteur
    // qui fais sens
    return userEntity.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return userEntity.isActive();
  }

}
