package com.example.demo.domain;

import com.example.demo.enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Table
@Entity
@Builder(builderClassName = "allBuilder", builderMethodName = "allBuilder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document extends BaseTimeColumn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Lob
  @Column(nullable = false)
  private String content;

  @OneToOne
  @JoinColumn(name = "division_code", nullable = false)
  private Division division;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false , name = "document_state")
  private StateEnum state;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST},
      mappedBy = "document")
  private Set<PaymentComment> paymentCommentSet = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Document document = (Document) o;
    return Objects.equals(id, document.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Builder(builderMethodName = "initBuilder", builderClassName = "initBuilder")
  private Document(Long id, String title, String content, User user, Division division , StateEnum state) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.user = user;
    this.division = division;
    this.state = state;
  }
}
