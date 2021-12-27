package com.example.sse.web;

import com.example.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequiredArgsConstructor
public class SseResource {

    private final SseService sseService;

    @GetMapping(value = "/resource-uri")
    public SseEmitter handle() {
        return sseService.baseEvent();
    }

    @GetMapping(value = "fetch-dataset")
    public SseEmitter fetchDataSet(){
        return sseService.fetchDataSet();
    }

}
