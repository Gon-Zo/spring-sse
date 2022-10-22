package com.example.app.web;

import com.example.app.constract.State;
import com.example.app.service.ItemService;
import com.example.app.service.ProductService;
import com.example.app.service.dto.InitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductResource {

  private final ProductService productService;

  private final ItemService itemService;

  @PutMapping("/product")
  public ResponseEntity<State> initProductUser(
      @RequestBody InitDTO dto, @RequestHeader("X-USER-ID") Long userId) {
    State body = productService.initProductByUser(dto, userId);
    return ResponseEntity.ok(body);
  }

  @PutMapping("/item")
  public ResponseEntity<State> initItemUser(
      @RequestBody InitDTO dto, @RequestHeader("X-USER-ID") Long userId) {
    State body = itemService.initUser(dto, userId);
    return ResponseEntity.ok(body);
  }

  @PutMapping("/item2")
  public Integer initItemUser2(@RequestBody InitDTO dto, @RequestHeader("X-USER-ID") Long userId) {
    Integer body = itemService.updateState(dto, userId);
    return body;
  }

  @GetMapping("/test")
  public ResponseEntity<Long> testAPI(@RequestHeader("X-USER-ID") Long userId) {

    long start = System.currentTimeMillis();

    log.info("[Thread Name]={},[userId]={}", Thread.currentThread().getName(), userId);

    log.info("[Time]={}ms,[userId]={}", (System.currentTimeMillis() - start), userId);

    return ResponseEntity.ok(userId);
  }

  @GetMapping("/hash-code")
  public ResponseEntity<Long> hashCodeAPI(@RequestHeader("X-USER-ID") Long userId) {
    log.info("[hashCode]:{}", this.hashCode());
    return ResponseEntity.ok(userId);
  }
}
