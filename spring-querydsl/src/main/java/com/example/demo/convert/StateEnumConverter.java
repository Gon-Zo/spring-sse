package com.example.demo.convert;

import com.example.demo.enums.StateEnum;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class StateEnumConverter extends AbstractBaseEnumConverter<StateEnum, String> {
  @Override
  protected Class<StateEnum> getEnumClass() {
    return StateEnum.class;
  }
}
