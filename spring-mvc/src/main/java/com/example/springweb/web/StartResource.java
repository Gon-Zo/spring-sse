package com.example.springweb.web;

import com.example.springweb.domain.db1.CurrentDate;
import com.example.springweb.repository.db1.CurrentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/start")
public class StartResource {

  private final CurrentRepository currentRepository;

  @GetMapping()
  public ResponseEntity<List<CurrentDate>> getNowDate() {
    List<CurrentDate> body = currentRepository.findAll();
    return ResponseEntity.ok(body);
  }

  @GetMapping("/hello")
  public ResponseEntity<Boolean> getBooleanValue(Boolean isHello) {

    if (isHello) {
      // isHello 가 ture 로 넘겨지면 HttpStatus 200 반환
      return ResponseEntity.status(HttpStatus.OK).body(isHello);
    }

    // isHello 가 false 로 넘겨지면 HttpStatus 204 반환
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(isHello);
  }

  @GetMapping("/start")
  public ResponseEntity<String> getCustomHeader() {
    return ResponseEntity.status(HttpStatus.OK)
        .header("CUSTOM-HEADER", "this custom")
        .body("start");
  }

  @GetMapping("/bad")
  public ResponseEntity<String> getBadRes() {
    return ResponseEntity.badRequest().body("bad response");
  }
}
