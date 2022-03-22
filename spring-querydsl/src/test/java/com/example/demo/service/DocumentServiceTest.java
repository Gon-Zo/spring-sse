package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.mock.DocumentMock;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DocumentServiceTest {

  private DocumentService documentService;

  @Mock private DocumentRepository documentRepository;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    documentService = new DocumentServiceImpl(documentRepository);
  }

  @Test
  @Transactional
  void createdDocument() {

    User user = UserMock.createUser();

    Document mock = DocumentMock.createMock(user);

    BDDMockito.given(documentRepository.save(any())).willReturn(mock);

    Document entity = documentService.createdDocument(DocumentMock.createMockDTO(), user);

    BDDMockito.then(documentRepository).should().save(any());

    Assertions.assertEquals(entity.getTitle(), mock.getTitle());
    Assertions.assertEquals(entity.getContent(), mock.getContent());
    Assertions.assertEquals(entity.getUser(), mock.getUser());
  }
}
