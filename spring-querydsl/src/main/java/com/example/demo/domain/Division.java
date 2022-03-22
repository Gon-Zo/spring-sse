package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "master_division")
public class Division {

  @Id
  @Column(unique = true, nullable = false)
  private String code;

  @Column(nullable = false)
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Division division = (Division) o;
    return Objects.equals(code, division.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
