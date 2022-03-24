package com.example.demo.service;

import com.example.demo.domain.Division;
import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.mock.DivisionMock;
import com.example.demo.mock.DocumentMock;
import com.example.demo.mock.PaymentCommentMock;
import com.example.demo.mock.UserMock;
import com.example.demo.repository.DocumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DocumentServiceTest {

  private DocumentService documentService;

  @Mock private DocumentRepository documentRepository;

  private User user;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    documentService = new DocumentServiceImpl(documentRepository);
    user = UserMock.createUser();
  }

  @Test
  @Transactional
  void createdDocument() {

    Document mock = DocumentMock.createMock(user);

    mock.getPaymentCommentSet()
        .addAll(PaymentCommentMock.createPaymentCommentList(UserMock.createOtherUsers(), mock));

    Optional<Division> divisionMock = DivisionMock.createMockToOptional();

    BDDMockito.given(documentRepository.save(any())).willReturn(mock);

    Document entity = documentService.createdDocument(DocumentMock.createMockDTO(), user);

    BDDMockito.then(documentRepository).should().save(any());

    Assertions.assertEquals(entity.getTitle(), mock.getTitle());
    Assertions.assertEquals(entity.getContent(), mock.getContent());
    Assertions.assertEquals(entity.getUser(), mock.getUser());
    Assertions.assertEquals(entity.getDivision(), divisionMock.get());
  }
}
