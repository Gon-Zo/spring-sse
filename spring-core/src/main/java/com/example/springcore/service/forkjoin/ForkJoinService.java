package com.example.springcore.service.forkjoin;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ForkJoinService {

  private final ForkJoinPoolFactoryBean forkJoinPoolFactoryBean;

  public Integer getForkJoinTask() {

    ForkJoinPool pool = forkJoinPoolFactoryBean.getObject();

    List<Integer> list = IntStream.range(0, 10000).boxed().collect(Collectors.toList());

    ActiveTask task = new ActiveTask(list);

    Integer result = pool.invoke(task);

    if (task.isDone()) {
      // result
    }

    forkJoinPoolFactoryBean.destroy();

    return result;
  }
}
