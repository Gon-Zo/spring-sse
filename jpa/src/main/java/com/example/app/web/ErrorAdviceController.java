package com.example.app.web;

import com.example.app.constract.MsgType;
import com.example.app.service.dto.Message;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.StaleStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ErrorAdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StaleStateException.class)
    public Message handleException(StaleStateException e, HttpServletResponse response) {

        String code = MsgType.E001.getCode();

        String detailMessage = ExceptionUtils.getStackTrace(e);

        return Message.of(code, detailMessage);
    }
}
