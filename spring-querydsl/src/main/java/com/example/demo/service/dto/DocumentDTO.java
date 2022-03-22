package com.example.demo.service.dto;

import com.example.demo.domain.Division;
import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import lombok.*;

@Getter
@Builder(builderMethodName = "allBuilder", builderClassName = "allBuilder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDTO {

  private String title;

  private String content;

  public Document toEntity(User user) {
    return Document.initBuilder()
        .title(title)
        .content(content)
        .user(user)
        .division(new Division("c001", "test1"))
        .build();
  }
}
