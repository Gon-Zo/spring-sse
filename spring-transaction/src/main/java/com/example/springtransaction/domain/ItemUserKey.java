package com.example.springtransaction.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemUserKey implements Serializable {

  @Column(nullable = false)
  private Long itemId;

  @Column(nullable = false)
  private Long userId;
}
