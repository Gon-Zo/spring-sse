package com.example.demo.mock;

import com.example.demo.domain.Division;

import java.util.List;

public class DivisionMock {

  public static List<Division> createMasterDivision() {
    return List.of(
        new Division("c001", "test1"),
        new Division("c002", "test2"),
        new Division("c003", "test3"),
        new Division("c004", "test4"));
  }
}
