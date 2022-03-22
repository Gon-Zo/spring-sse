package com.example.demo.domain.projection;

import java.util.Set;

public interface IDocument {

  Long getId();

  String getTitle();

  String getContent();

  IUser getUser();

  Set<IPaymentComment> getPaymentCommentSet();
}
