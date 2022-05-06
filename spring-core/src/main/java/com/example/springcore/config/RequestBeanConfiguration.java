package com.example.springcore.config;

import com.example.springcore.service.dto.ValueDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class RequestBeanConfiguration {

  @Bean
  @RequestScope
  public ValueDTO valueDTO() {
    return new ValueDTO("test1", "test2");
  }
}
