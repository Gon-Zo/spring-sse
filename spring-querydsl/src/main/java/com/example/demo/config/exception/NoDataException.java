package com.example.demo.config.exception;

import com.example.demo.enums.MessageType;

public class NoDataException extends BaseException {

    public NoDataException(MessageType msgType) {
        super(msgType);
    }
}
