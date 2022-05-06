package com.example.demo.convert;

import com.example.demo.enums.RoleEnum;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleEnumConverter extends AbstractBaseEnumConverter<RoleEnum, String> {
  @Override
  protected Class<RoleEnum> getEnumClass() {
    return RoleEnum.class;
  }
}
