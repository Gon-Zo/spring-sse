package com.example.springtransaction.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"itemId", "userId"})
public class ItemUserKey implements Serializable {

  private Long itemId;

  private Long userId;
}
