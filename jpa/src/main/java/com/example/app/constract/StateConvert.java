package com.example.app.constract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class StateConvert implements AttributeConverter<State, String> {
  @Override
  public String convertToDatabaseColumn(State attribute) {
    return Optional.ofNullable(attribute).orElseThrow().name();
  }

  @Override
  public State convertToEntityAttribute(String dbData) {
    return State.valueOf(Optional.ofNullable(dbData).orElseThrow());
  }
}
