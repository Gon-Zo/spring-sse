package com.example.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {
        "com.example.springweb"
})
public class SpringMvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMvcApplication.class, args);
  }

  @PostConstruct
  public void initApplication() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
