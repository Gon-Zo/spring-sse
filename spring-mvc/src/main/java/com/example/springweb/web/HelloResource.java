package com.example.springweb.web;

import com.example.springweb.web.dto.HelloDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

  @PostMapping("/api/test")
  public HelloDTO showHelloDTO(@RequestBody HelloDTO dto) {
    return dto;
  }
}
