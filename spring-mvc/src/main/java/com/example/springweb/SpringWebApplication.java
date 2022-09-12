package com.example.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {
        "com.example.springweb"
})
public class SpringWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringWebApplication.class, args);
  }

  @PostConstruct
  public void initApplication() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
