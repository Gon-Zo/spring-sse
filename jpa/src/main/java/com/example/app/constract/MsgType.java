package com.example.app.constract;

import lombok.Getter;

@Getter
public enum MsgType {

    E001("E001");

    private final String code;

    MsgType(String code) {
        this.code = code;
    }
}
