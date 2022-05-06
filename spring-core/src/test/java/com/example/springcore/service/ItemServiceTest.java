package com.example.springcore.service;

import com.example.springcore.config.base.Item1;
import com.example.springcore.service.external.AbstractExternalAction;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("A")
class ItemServiceTest {

  @Autowired private AbstractExternalAction abstractExternalAction;

  @Autowired private Item1 item1;

  @Nested
  class Base {
    @Test
    void test() {
      item1.print();
    }
  }
}
