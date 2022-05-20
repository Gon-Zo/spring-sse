package com.example.springcore.web;

import com.example.springcore.config.aspect.Logging;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

  @Logging
  @GetMapping("/aop")
  public String getStr() {
    return "str";
  }
}
