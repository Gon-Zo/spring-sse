package com.example.demo.enums;

public enum MessageType {
  NoRoleData("M001", "role enum data null"),
  NoBoxTypeData("M002", "box type is null "),
  NoSelectedDivisionData("M003", "master division data not found"),
  NoPaymentCommentUser("M004", "document's payment comment is empty data");

  private final String code;
  private final String message;

  MessageType(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
