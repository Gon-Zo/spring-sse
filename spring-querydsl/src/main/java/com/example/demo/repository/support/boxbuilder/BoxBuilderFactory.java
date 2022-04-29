package com.example.demo.repository.support.boxbuilder;

import com.example.demo.config.exception.NoDataException;
import com.example.demo.enums.MessageType;
import com.querydsl.core.BooleanBuilder;

import java.util.Optional;

public class BoxBuilderFactory implements BoxBuilder, BoxFactory {

  private AbstractBoxBuilder boxBuilder;

  @Override
  public BoxBuilderFactory getBoxAction(BoxType boxType, Long userId) {

    BoxType switchType = Optional.ofNullable(boxType).orElse(BoxType.NULL);

    switch (switchType) {
      case OUTBOX:
        boxBuilder = new OutBoxBuilder(userId);
        break;
      case ARCHIVE:
        boxBuilder = new ArchiveBoxBuilder(userId);
        break;
      case INBOX:
        boxBuilder = new InBoxBuilder(userId);
        break;
      default:
        throw new NoDataException(MessageType.NoBoxTypeData);
    }

    return this;
  }

  @Override
  public BooleanBuilder getBoxListInWhere() {
    return this.boxBuilder.getBoxListInWhere();
  }
}
