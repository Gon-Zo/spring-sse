package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class CacheService {

  @Cacheable(value = "cacheStore", key = "#number")
  public BigDecimal getNum(Integer number) {
    BigDecimal square = BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(number));
    log.info("square of {} is {}", number, square);
    return square;
  }
}
