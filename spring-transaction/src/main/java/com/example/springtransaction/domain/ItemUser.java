package com.example.springtransaction.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder(builderMethodName = "initBuilder", builderClassName = "initBuilder")
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUser {

  @EmbeddedId private ItemUserKey id;

  @MapsId("itemId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Item item;

  private Integer amount;

  @PrePersist
  void prePersist() {
    if (null == this.amount) this.amount = 0;
  }
}
