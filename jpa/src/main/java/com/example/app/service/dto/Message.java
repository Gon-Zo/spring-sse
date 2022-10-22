package com.example.app.service.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    private String code;

    private String detail;
   
    public static Message of(String code, String detail) {
        return new Message(code, detail);
    }
}

