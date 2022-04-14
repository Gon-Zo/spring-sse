package com.example.springweb.serivce;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncService {

  private final Executor threadPool;

  /**
   * @return
   */
  public CompletableFuture<List<Integer>> onAsync() {

    List<Integer> result = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {

      final int num = i;

      threadPool.execute(
          () -> {
            log.info("[threadName]:{}", Thread.currentThread().getName());

            List<Integer> resultValueList =
                IntStream.range(num, num * 100).boxed().collect(Collectors.toList());

            result.addAll(resultValueList);
          });
    }

    return CompletableFuture.completedFuture(result);
  }
}
