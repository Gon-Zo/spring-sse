package com.example.demo.repository.support.boxaction;

import com.example.demo.config.exception.NoDataException;
import com.example.demo.enums.MessageType;
import com.querydsl.core.BooleanBuilder;

import java.util.Optional;

public class BoxActionFactory {

  private IBoxAction boxAction;

  public BooleanBuilder getBoxListInWhere() {
    return boxAction.getBoxListInWhere();
  }

  public BoxActionFactory() {}

  private BoxActionFactory(IBoxAction iBoxAction) {
    this.boxAction = iBoxAction;
  }

  public BoxActionFactory getBoxAction(BoxType boxType, Long userId) {

    BoxType switchType = Optional.ofNullable(boxType).orElse(BoxType.NULL);

    switch (switchType) {
      case OUTBOX:
        boxAction = new OutBoxAction(userId);
        break;
      case ARCHIVE:
        boxAction = new ArchiveBoxAction(userId);
        break;
      case INBOX:
        boxAction = new InBoxAction(userId);
        break;
      default:
        throw new NoDataException(MessageType.NoBoxTypeData);
    }

    return this;
  }
}
