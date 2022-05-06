package com.example.springcore.web;

import com.example.springcore.service.external.AbstractExternalAction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActionResource {

  private final AbstractExternalAction action;

  @GetMapping("/test")
  public String action() {
    return action.action();
  }
}
