package com.example.forkjoinpool.service.task;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ActiveAction extends AbstRecursiveAction<ActiveAction> {

  private final List<Integer> list;

  public ActiveAction(List<Integer> list) {
    this.list = list;
  }

  @Override
  protected List<ActiveAction> createSubtasks() {

    List<ActiveAction> subtasks = new ArrayList<>();

    int listSize = list.size();

    int standardSize = listSize / 2;

    subtasks.add(new ActiveAction(list.subList(0, standardSize)));

    subtasks.add(new ActiveAction(list.subList(standardSize, listSize)));

    return subtasks;
  }

  @Override
  protected void compute() {

    if (list.size() > 1000) {

      List<ActiveAction> subtasks = new ArrayList<>(createSubtasks());

      for (ActiveAction activeAction : subtasks) {
        activeAction.fork();
      }

    } else {
      int sum = list.stream().mapToInt(i -> i).sum();
      log.info("action sum value {}", sum);
    }
  }
}
