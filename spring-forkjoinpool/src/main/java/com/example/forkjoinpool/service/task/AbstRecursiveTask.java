package com.example.forkjoinpool.service.task;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public abstract class AbstRecursiveTask<T, V> extends RecursiveTask<V> {

  protected void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected abstract List<T> createSubtasks();
}
