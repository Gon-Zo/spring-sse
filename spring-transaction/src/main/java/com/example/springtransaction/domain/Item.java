package com.example.springtransaction.domain;

import com.example.springtransaction.constract.State;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@DynamicUpdate
@Table(name = "product_1")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
  @MapKey(name = "id")
  private Set<ItemUser> productUserSet = new HashSet<>();

  @Version private Integer version;

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
