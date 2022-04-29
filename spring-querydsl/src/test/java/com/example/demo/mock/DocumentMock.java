package com.example.demo.mock;

import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.User;
import com.example.demo.repository.support.boxbuilder.BoxType;
import com.example.demo.service.dto.DocumentBoxDTO;
import com.example.demo.service.dto.DocumentDTO;

import java.util.List;

public class DocumentMock {

  private static final Long DEFAULT_ID = 1L;

  private static final String DEFAULT_TITLE = "..";

  private static final String DEFAULT_CONTENT = "............";

  public static Document createMock(User user) {
    return Document.initBuilder()
        .id(DEFAULT_ID)
        .title(DEFAULT_TITLE)
        .content(DEFAULT_CONTENT)
        .division(DivisionMock.createMock())
        .user(user)
        .build();
  }

  public static Document createAllMock(User user) {

    Document mock =
        Document.initBuilder()
            .id(DEFAULT_ID)
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .user(user)
            .build();

    List<PaymentComment> paymentCommentList =
        PaymentCommentMock.createPaymentCommentList(UserMock.createOtherUsers(), mock);

    mock.getPaymentCommentSet().addAll(paymentCommentList);

    return mock;
  }

  public static DocumentDTO createMockDTO() {
    return DocumentDTO.allBuilder()
        .title(DEFAULT_TITLE)
        .content(DEFAULT_CONTENT)
        .divisionCode("c001")
        .userIdList(List.of(2, 3))
        .build();
  }

  public static DocumentBoxDTO createdDocumentBoxDTOMock() {
    return DocumentBoxDTO.allBuilder().page(0).size(10).type(BoxType.ARCHIVE).build();
  }

}
