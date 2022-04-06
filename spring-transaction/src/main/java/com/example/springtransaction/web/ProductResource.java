package com.example.springtransaction.web;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.service.ProductService;
import com.example.springtransaction.service.dto.InitDTO;
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

  @PutMapping("/product")
  public ResponseEntity<State> initProductUser(
      @RequestBody InitDTO dto, @RequestHeader("X-USER-ID") Long userId) {
    State body = productService.initProductByUser(dto, userId);
    return ResponseEntity.ok(body);
  }

  @GetMapping("/test")
  public ResponseEntity<Long> test(@RequestHeader("X-USER-ID") Long userId) {

    long start = System.currentTimeMillis();

    log.info("[Thread Name]={},[userId]={}", Thread.currentThread().getName(), userId);

    log.info("[Time]={}ms,[userId]={}", (System.currentTimeMillis() - start), userId);

    return ResponseEntity.ok(userId);
  }
}
