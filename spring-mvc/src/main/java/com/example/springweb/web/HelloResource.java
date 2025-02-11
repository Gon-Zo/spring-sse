package com.example.springweb.web;

import com.example.springweb.serivce.AsyncService;
import com.example.springweb.serivce.dto.HelloDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloResource {

  private final AsyncService asyncService;

  @PostMapping("/api/test")
  public HelloDTO showHelloDTO(@RequestBody HelloDTO dto) {
    return dto;
  }

  @GetMapping("/asyn")
  public List<Integer> onAsync() {
    try {
      return asyncService.onAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
