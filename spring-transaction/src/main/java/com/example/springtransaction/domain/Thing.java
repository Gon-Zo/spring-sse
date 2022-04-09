package com.example.springtransaction.domain;

import com.example.springtransaction.constract.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicUpdate
@Table(name = "product_2")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Integer totalAmount;

  @Column(nullable = false)
  private Integer currentAmount;

  @Column(nullable = false)
  private State state;

  private Integer version;

  @PrePersist
  void prePersist() {
    if (null == this.totalAmount) this.totalAmount = 0;
    if (null == this.currentAmount) this.currentAmount = 0;
    this.state = State.PROGRESS;
  }

  @Transient
  public void upCurrentAmount(Integer amount) {
    this.currentAmount += amount;
  }

  @Transient
  public void soldOutState() {
    this.state = State.SOLDOUT;
  }
}
