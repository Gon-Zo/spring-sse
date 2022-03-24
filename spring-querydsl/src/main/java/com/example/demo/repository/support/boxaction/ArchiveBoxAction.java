package com.example.demo.repository.support.boxaction;

import com.example.demo.enums.StateEnum;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDocument.document;
import static com.example.demo.domain.QPaymentComment.paymentComment;

public class ArchiveBoxAction extends AbstractBoxAction implements IBoxAction {

  public ArchiveBoxAction(Long userId) {
    super(userId);
  }

  @Override
  public BooleanBuilder getBoxListInWhere() {

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    Long userId = getUserId();

    booleanBuilder.and(document.user.id.eq(userId).or(paymentComment.id.userId.eq(userId)));

    booleanBuilder.and(document.state.in(StateEnum.OK, StateEnum.NO));

    return booleanBuilder;
  }
}
