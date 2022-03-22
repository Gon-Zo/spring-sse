package com.example.demo.domain;

import com.example.demo.enums.StateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Table
@Entity
@Builder(builderClassName = "allBuilder", builderMethodName = "allBuilder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentComment {

  @EmbeddedId private PaymentCommentKey id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("documentId")
  private Document document;

  @ManyToOne
  @MapsId("userId")
  private User user;

  @Column(nullable = false, name = "payment_state")
  private StateEnum state;

  @Lob private String comment;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentComment that = (PaymentComment) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
