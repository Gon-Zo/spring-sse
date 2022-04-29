package com.example.demo.web;

import com.example.demo.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EhcacheResource {

  private final CacheService cacheService;

  @GetMapping("/test/{number}")
  public BigDecimal getNumber(@PathVariable Integer number) {
    return cacheService.getNum(number);
  }
}
