package com.example.demo.domain;

import com.example.demo.Demo1Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@SpringBootTest(classes = Demo1Application.class)
class ItemTest {

  @Autowired EntityManagerFactory entityManagerFactory;

  @Nested
  @DisplayName("영속성 hashcode 관계")
  class HashCode {

    @Test
    @DisplayName("영속성 hashcode & equals 테스트")
    void findId() {

      EntityManager entityManager = entityManagerFactory.createEntityManager();

      EntityTransaction transaction = entityManager.getTransaction();

      transaction.begin();

      Item mock = Item.builder().name("test1").build();

      entityManager.persist(mock);

      transaction.commit();

      entityManager.clear();

      Item item = entityManager.find(Item.class, mock.getId());

      Assertions.assertEquals(item, mock);
      // hashCode 비교
      Assertions.assertEquals(item.hashCode(), mock.hashCode());
    }
  }
//  https://hyeooona825.tistory.com/87
  @Nested
  @DisplayName("비영속성")
  class New {

    @Test
    void newEntity() {

      // 그냥 new 생성 연산자를 사용을 하면 비 영속성 상태가 된다.
      Item mock = Item.builder().name("test1").build();
    }
  }

  @Nested
  @DisplayName("영속성")
  class Managed {

  }
}
