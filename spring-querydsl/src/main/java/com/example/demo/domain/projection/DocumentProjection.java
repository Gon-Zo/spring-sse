package com.example.demo.domain.projection;

import com.example.demo.enums.StateEnum;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class DocumentProjection {

  private Long id;

  private String title;

  private String content;

  private Date createdDate;

  private Date updatedDate;

  private String writer;

  private String divisionName;

  private StateEnum state;

  private List<CommentProjection> paymentCommentSet;
}
