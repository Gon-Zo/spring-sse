package com.example.demo.domain.projection;

import com.example.demo.enums.RoleEnum;

public interface IUser {
  Long getId();

  String getEmail();

  String getPassword();

  RoleEnum getRole();
}
