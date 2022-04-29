package com.example.demo.repository.support.boxbuilder;

import com.example.demo.enums.StateEnum;
import com.querydsl.core.BooleanBuilder;

import static com.example.demo.domain.QDocument.document;

public final class OutBoxBuilder extends AbstractBoxBuilder {

  OutBoxBuilder(Long userId) {
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
