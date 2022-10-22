package com.example.app.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.StaleStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(StaleStateException.class)
  public Message handleException(StaleStateException e, HttpServletResponse response) {

    String code = MsgType.E001.code;

    String detailMessage = ExceptionUtils.getStackTrace(e);

    return Message.builder().code(code).detail(detailMessage).build();
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static class Message {
    private String code;
    private String detail;
  }

  enum MsgType {
    E001("E001");
    private final String code;

    MsgType(String code) {
      this.code = code;
    }
  }
}
