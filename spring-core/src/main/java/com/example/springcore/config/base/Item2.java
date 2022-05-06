package com.example.springcore.config.base;

import org.springframework.stereotype.Component;

@Component
public class Item2 implements Print {

  @Override
  public void print() {
    System.out.println("item2");
  }
}
