package com.example.demo.domain.projection;


import com.example.demo.enums.StateEnum;
import org.springframework.beans.factory.annotation.Value;

public interface IPaymentComment {

  @Value("#{target.id.documentId}")
  Long getDocumentId();

  @Value("#{target.id.userId}")
  Long userId();

  @Value("#{target.paymentState}")
  StateEnum getState();

  String getComment();
}
