package com.example.demo.mock;

import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.PaymentCommentKey;
import com.example.demo.domain.User;
import com.example.demo.enums.StateEnum;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentCommentMock {

  public static List<PaymentComment> createPaymentCommentList(
      List<User> otherUsers, Document document) {
    return otherUsers.stream()
        .map(
            user ->
                PaymentComment.allBuilder()
                    .id(
                        PaymentCommentKey.allBuilder()
                            .userId(user.getId())
                            .documentId(document.getId())
                            .build())
                    .user(user)
                    .document(document)
                    .state(StateEnum.NONE)
                    .build())
        .collect(Collectors.toList());
  }
}
