package com.example.demo.repository.support.boxbuilder;

import com.example.demo.enums.StateEnum;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDocument.document;
import static com.example.demo.domain.QPaymentComment.paymentComment;

public final class InBoxBuilder extends AbstractBoxBuilder {

  InBoxBuilder(Long userId) {
    super(userId);
  }

  @Override
  public BooleanBuilder getBoxListInWhere() {

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    Long userId = getUserId();

    booleanBuilder.and(
        paymentComment.id.userId.eq(userId).and(paymentComment.step.eq(document.step)));

    booleanBuilder.and(document.state.eq(StateEnum.DEFAULT));

    return booleanBuilder;
  }
}
