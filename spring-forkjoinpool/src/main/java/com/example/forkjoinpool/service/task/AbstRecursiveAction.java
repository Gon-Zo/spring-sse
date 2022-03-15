package com.example.forkjoinpool.service.task;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public abstract class AbstRecursiveAction<T> extends RecursiveAction {

  protected void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected abstract List<T> createSubtasks();
}
