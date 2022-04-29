package com.example.demo.repository.support.boxbuilder;

public abstract class AbstractBoxBuilder implements BoxBuilder {

  private final Long userId;

  protected AbstractBoxBuilder(Long userId) {
    this.userId = userId;
  }

  public final Long getUserId() {
    return userId;
  }
}
