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
public class ProductUserKey implements Serializable {

  @Column(nullable = false)
  private Long productId;

  @Column(nullable = false)
  private Long userId;
}
