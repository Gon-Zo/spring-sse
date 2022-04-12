package com.example.springweb.web;

import com.example.springweb.serivce.AsyncService;
import com.example.springweb.web.dto.HelloDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloResource {

  private final AsyncService asyncService;

  @PostMapping("/api/test")
  public HelloDTO showHelloDTO(@RequestBody HelloDTO dto) {
    return dto;
  }

  @GetMapping("/asyn")
  public String onAsync() {
    asyncService.onAsync();
    return "success";
  }
}
