package com.example.demo.enums;

public enum RoleEnum implements BaseEnum<String> {
  ROLE_ADMIN,
  ROLE_USER;

  @Override
  public String getValue() {
    return this.name();
  }
}
