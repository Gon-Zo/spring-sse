package com.example.springtransaction.web;

import com.example.springtransaction.constract.State;
import com.example.springtransaction.service.ProductService;
import com.example.springtransaction.service.dto.InitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
