package com.example.forkjoinpool.web;

import com.example.forkjoinpool.service.ForkJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fork-join-pool")
@RequiredArgsConstructor
public class ForkJoinPollResource {

  private final ForkJoinService forkJoinService;

  @GetMapping("/task")
  public Integer showPoolTask() {
    return forkJoinService.getForkJoinTask();
  }
}
