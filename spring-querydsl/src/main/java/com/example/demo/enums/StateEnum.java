package com.example.demo.enums;

public enum StateEnum implements BaseEnum<String> {
  NO,
  OK,
  DEFAULT;

  @Override
  public String getValue() {
    return this.name();
  }
}
