package com.example.springtransaction.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Builder(builderMethodName = "initBuilder", builderClassName = "initBuilder")
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@IdClass(value = ItemUserKey.class)
public class ItemUser {

//  @Id private Long itemId;
//
//  @Id private Long userId;

  @EmbeddedId
  private ItemUserKey id;

  private Integer amount;

  @PrePersist
  void prePersist() {
    if (null == this.amount) this.amount = 0;
  }
}
