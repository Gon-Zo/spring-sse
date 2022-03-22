package com.example.demo.service.dto;

import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.PaymentCommentKey;
import com.example.demo.domain.User;
import com.example.demo.enums.StateEnum;

public class PaymentCommentDTO {

  public PaymentComment toEntity(User user, Document document) {

    return PaymentComment.allBuilder()
        .id(setPaymentCommentKey(user, document))
        .user(user)
        .document(document)
        .state(StateEnum.NONE)
        .build();
  }

  private PaymentCommentKey setPaymentCommentKey(User user, Document document) {
    return PaymentCommentKey.allBuilder().userId(user.getId()).documentId(document.getId()).build();
  }
}
