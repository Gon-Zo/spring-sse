package com.example.springcore.service.forkjoin;

import java.util.ArrayList;
import java.util.List;

public class ActiveTask extends AbstRecursiveTask<ActiveTask, Integer> {

  private final List<Integer> list;

  public ActiveTask(List<Integer> list) {
    this.list = list;
  }

  @Override
  protected Integer compute() {

    if (list.size() > 100) {
      sleep(1000);

      List<ActiveTask> subtasks = new ArrayList<>(createSubtasks());

      for (ActiveTask activeTask : subtasks) {
        activeTask.fork();
      }

      Integer sum = 0;

      for (ActiveTask activeTask : subtasks) {
        Integer result = activeTask.join();
        sum += result;
      }

      return sum;
    } else {

      sleep(1000);

      return list.stream().mapToInt(v -> v).sum();
    }
  }

  @Override
  protected List<ActiveTask> createSubtasks() {

    List<ActiveTask> subtasks = new ArrayList<>();

    int listSize = list.size();

    int standardSize = listSize / 2;

    subtasks.add(new ActiveTask(list.subList(0, standardSize)));

    subtasks.add(new ActiveTask(list.subList(standardSize, listSize)));

    return subtasks;
  }
}
