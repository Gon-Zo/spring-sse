package com.example.springweb.web;

import com.example.springweb.domain.CurrentDate;
import com.example.springweb.repository.CurrentRepository;
import lombok.RequiredArgsConstructor;
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
  public List<CurrentDate> getNowDate() {
    return currentRepository.findAll();
  }
}
