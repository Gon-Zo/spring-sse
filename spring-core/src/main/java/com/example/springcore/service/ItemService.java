package com.example.springcore.service;

import com.example.springcore.config.base.Item1;
import com.example.springcore.config.base.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements Print {

  @Autowired private Item1 item1;

  @Override
  public void print() {
    item1.print();
  }
}
