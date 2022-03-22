package com.example.demo.repository;

import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.User;
import com.example.demo.mock.DocumentMock;
import com.example.demo.mock.PaymentCommentMock;
import com.example.demo.mock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class PaymentCommentRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Autowired private DocumentRepository documentRepository;

  @Autowired private PaymentCommentRepository paymentCommentRepository;

  private User user;

  private List<User> otherUsers;

  private Document document;

  @BeforeEach
  void init() {

    user = userRepository.save(UserMock.createUser());

    otherUsers = userRepository.saveAll(UserMock.createOtherUsers());

    document = documentRepository.save(DocumentMock.createMock(user));
  }

  @Test
  void saveAll() {

    List<PaymentComment> mock = PaymentCommentMock.createPaymentCommentList(otherUsers, document);

    List<PaymentComment> entities = paymentCommentRepository.saveAll(mock);

    paymentCommentRepository.flush();

    org.assertj.core.api.Assertions.assertThat(entities).isEqualTo(mock);
    Assertions.assertEquals(entities.size(), mock.size());
    Assertions.assertFalse(entities.isEmpty());
    Assertions.assertEquals(entities.get(0).getId(), mock.get(0).getId());
    Assertions.assertEquals(entities.get(0).getState(), mock.get(0).getState());
    Assertions.assertEquals(entities.get(0).getComment(), mock.get(0).getComment());
  }
}
