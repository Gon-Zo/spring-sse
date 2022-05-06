package com.example.demo.convert;

import com.example.demo.enums.BaseEnum;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public abstract class AbstractBaseEnumConverter<E extends BaseEnum<T>, T>
    implements AttributeConverter<E, T> {

  protected abstract Class<E> getEnumClass();

  protected final E foundOf(T s) {

    E[] enums = (E[]) getEnumClass().getEnumConstants();

    for (E e : enums) {
      if (e.getValue().equals(s)) {
        return e;
      }
    }

    return null;
  }

  @Override
  public T convertToDatabaseColumn(E attribute) {
    return Optional.ofNullable(attribute).orElseThrow(NullPointerException::new).getValue();
  }

  @Override
  public E convertToEntityAttribute(T dbData) {
    return foundOf(dbData);
  }
}
