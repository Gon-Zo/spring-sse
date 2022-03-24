package com.example.demo.repository.support.boxaction;

public abstract class AbstractBoxAction {

  private final Long userId;

  protected AbstractBoxAction(Long userId) {
    this.userId = userId;
  }

  protected Long getUserId() {
    return userId;
  }
}
