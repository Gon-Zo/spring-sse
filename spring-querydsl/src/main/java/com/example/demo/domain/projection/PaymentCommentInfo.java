package com.example.demo.domain.projection;

import com.example.demo.enums.StateEnum;
import lombok.Getter;

@Getter
public class PaymentCommentInfo {

  private Long userId;

  private String userEmail;

  private String comment;

  private Integer step;

  private StateEnum state;
}
