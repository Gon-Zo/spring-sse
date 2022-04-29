package com.example.demo.repository;

import com.example.demo.config.QueryDslConfiguration;
import com.example.demo.domain.Division;
import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.User;
import com.example.demo.domain.projection.IDocument;
import com.example.demo.enums.StateEnum;
import com.example.demo.mock.DivisionMock;
import com.example.demo.mock.DocumentMock;
import com.example.demo.mock.PaymentCommentMock;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.support.boxbuilder.BoxBuilderFactory;
import com.example.demo.repository.support.boxbuilder.BoxType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(QueryDslConfiguration.class)
class DocumentRepositoryTest {

  @Autowired private DocumentRepository documentRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private DivisionRepository divisionRepository;

  private User user;

  private List<Division> divisions;

  private List<User> otherUsers;

  @BeforeEach
  void init() {

    divisions = divisionRepository.saveAll(DivisionMock.createMasterDivision());

    divisionRepository.flush();

    user = userRepository.save(UserMock.createUser());
    otherUsers = userRepository.saveAll(UserMock.createOtherUsers());

    userRepository.flush();
  }

  @Test
  void save() {

    Document mock = DocumentMock.createMock(user);

    Document entity = documentRepository.save(mock);

    List<PaymentComment> paymentCommentList =
        PaymentCommentMock.createPaymentCommentListToInit(otherUsers, entity);

    entity.getPaymentCommentSet().addAll(paymentCommentList);

    mock.getPaymentCommentSet().addAll(paymentCommentList);

    org.assertj.core.api.Assertions.assertThat(entity).isEqualTo(mock);

    Assertions.assertEquals(mock.getId(), entity.getId());
    Assertions.assertEquals(mock.getTitle(), entity.getTitle());
    Assertions.assertEquals(mock.getContent(), entity.getContent());
    Assertions.assertEquals(mock.getUser(), entity.getUser());
    Assertions.assertEquals(mock.getPaymentCommentSet(), entity.getPaymentCommentSet());
    Assertions.assertEquals(entity.getStep(), 1);
    Assertions.assertEquals(entity.getState(), StateEnum.DEFAULT);
  }

  @Nested
  class Select {

    private Document mock;

    @BeforeEach
    void init() {

      mock = documentRepository.save(DocumentMock.createMock(user));

      List<PaymentComment> paymentCommentList =
          PaymentCommentMock.createPaymentCommentList(otherUsers, mock);

      mock.getPaymentCommentSet().addAll(paymentCommentList);

      documentRepository.flush();
    }

    @Test
    void findById_projection() {

      Optional<IDocument> entityOptional =
          documentRepository.findById(mock.getId(), IDocument.class);

      Assertions.assertTrue(entityOptional.isPresent());

      IDocument entity = entityOptional.get();

      Assertions.assertEquals(mock.getId(), entity.getId());
      Assertions.assertEquals(mock.getTitle(), entity.getTitle());
      Assertions.assertEquals(mock.getContent(), entity.getContent());

      Assertions.assertEquals(mock.getUser().getId(), entity.getUser().getId());
      Assertions.assertEquals(mock.getUser().getEmail(), entity.getUser().getEmail());
      Assertions.assertEquals(mock.getUser().getPassword(), entity.getUser().getPassword());
      Assertions.assertEquals(mock.getUser().getRole(), entity.getUser().getRole());
    }

    @Test
    void findByUser_Id_projection() {
      Optional<IDocument> entityOptional =
          documentRepository.findByUser_Id(user.getId(), IDocument.class);

      Assertions.assertTrue(entityOptional.isPresent());

      IDocument entity = entityOptional.get();

      Assertions.assertEquals(mock.getId(), entity.getId());
      Assertions.assertEquals(mock.getTitle(), entity.getTitle());
      Assertions.assertEquals(mock.getContent(), entity.getContent());

      Assertions.assertEquals(mock.getUser().getId(), entity.getUser().getId());
      Assertions.assertEquals(mock.getUser().getEmail(), entity.getUser().getEmail());
      Assertions.assertEquals(mock.getUser().getPassword(), entity.getUser().getPassword());
      Assertions.assertEquals(mock.getUser().getRole(), entity.getUser().getRole());
      Assertions.assertEquals(
          mock.getPaymentCommentSet().size(), entity.getPaymentCommentSet().size());
    }

    @Test
    void findBy_OutBox() {

      PageRequest pageable = PageRequest.of(0, 10);

      BoxBuilderFactory factory =
          new BoxBuilderFactory().getBoxAction(BoxType.OUTBOX, user.getId());

      documentRepository.findByBoxAction(pageable, factory);
    }

    @Test
    void findBy_InBox() {

      PageRequest pageable = PageRequest.of(0, 10);

      BoxBuilderFactory factory = new BoxBuilderFactory().getBoxAction(BoxType.INBOX, user.getId());

      documentRepository.findByBoxAction(pageable, factory);
    }

    @Test
    void findBy_Archive() {
      PageRequest pageable = PageRequest.of(0, 10);

      BoxBuilderFactory factory =
          new BoxBuilderFactory().getBoxAction(BoxType.ARCHIVE, user.getId());

      documentRepository.findByBoxAction(pageable, factory);
    }
  }
}
