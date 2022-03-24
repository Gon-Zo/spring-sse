package com.example.demo.repository.support.boxaction;

import com.example.demo.enums.StateEnum;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDocument.document;

public class OutBoxAction extends AbstractBoxAction implements IBoxAction {

  public OutBoxAction(Long userId) {
    super(userId);
  }

  @Override
  public BooleanBuilder getBoxListInWhere() {

    BooleanBuilder booleanBuilder = new BooleanBuilder();

    Long userId = getUserId();

    booleanBuilder.and(document.user.id.eq(userId).and(document.state.eq(StateEnum.DEFAULT)));

    return booleanBuilder;
  }
}
