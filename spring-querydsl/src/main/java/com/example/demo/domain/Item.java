package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Table
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  //https://stackoverflow.com/questions/5031614/the-jpa-hashcode-equals-dilemma
}
