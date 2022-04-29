package com.example.demo.service.dto;

import com.example.demo.repository.support.boxbuilder.BoxType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allBuilder" , builderClassName = "allBuilder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentBoxDTO {

  private final BoxType type;

  private final Integer size;

  private final Integer page;
}
