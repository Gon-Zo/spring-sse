package com.example.demo.mock;

import com.example.demo.domain.Document;
import com.example.demo.domain.PaymentComment;
import com.example.demo.domain.PaymentCommentKey;
import com.example.demo.domain.User;
import com.example.demo.enums.StateEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaymentCommentMock {

  public static List<PaymentComment> createPaymentCommentList(
      List<User> otherUsers, Document document) {

    return IntStream.range(0, otherUsers.size())
        .mapToObj(
            num -> {
              User user = otherUsers.get(num);
              return PaymentComment.allBuilder()
                  .id(
                      PaymentCommentKey.allBuilder()
                          .userId(user.getId())
                          .documentId(document.getId())
                          .build())
                  .step(num + 1)
                  .user(user)
                  .document(document)
                  .state(StateEnum.DEFAULT)
                  .build();
            })
        .collect(Collectors.toList());
  }

  public static List<PaymentComment> createPaymentCommentListToInit(
      List<User> otherUsers, Document document) {

        return IntStream.range(0, otherUsers.size())
                .mapToObj(
                        num -> {
                            User user = otherUsers.get(num);
                            return PaymentComment.allBuilder()
                                    .id(
                                            PaymentCommentKey.allBuilder()
                                                    .userId(user.getId())
                                                    .documentId(document.getId())
                                                    .build())
                                    .step(num + 1)
                                    .state(StateEnum.DEFAULT)
                                    .build();
                        })
                .collect(Collectors.toList());
    }

}
