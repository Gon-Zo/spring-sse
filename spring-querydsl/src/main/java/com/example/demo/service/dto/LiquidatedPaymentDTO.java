package com.example.demo.service.dto;

import com.example.demo.enums.StateEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "allBuilder", builderClassName = "allBuilder")
public class LiquidatedPaymentDTO {

  @NotEmpty private final Long documentId;

  @NotEmpty private final String comment;

  @NotEmpty private final StateEnum state;
}
