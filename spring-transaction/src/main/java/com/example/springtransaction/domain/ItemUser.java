package com.example.springtransaction.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Builder(builderMethodName = "initBuilder", builderClassName = "initBuilder")
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(value = ItemUserKey.class)
public class ItemUser {

  @Id private Long itemId;

  @Id private Long userId;

  private Integer amount;

  @PrePersist
  void prePersist() {
    if (null == this.amount) this.amount = 0;
  }
}
