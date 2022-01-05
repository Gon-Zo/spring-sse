package com.example.sse.web;

import com.example.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SseResource {

    private final SseService sseService;

    @GetMapping(value = "/api/resource-uri")
    public SseEmitter handle() {
        return sseService.baseEvent();
    }

    @GetMapping(value = "/api/fetch-dataset")
    public SseEmitter fetchDataSet() {
        return sseService.fetchDataSet();
    }

    @GetMapping("/api/subscribe")
    public SseEmitter subscribe(String id) {
        return sseService.onSubscribe(id);
    }

    @GetMapping("/api/publish")
    public void publish(String message) {
        sseService.onPublish(message);
    }

    @GetMapping("/api/subscribe-group")
    public SseEmitter onSubscribeByGroup(String groupId){
        return sseService.onSubscribeByGroup(groupId);
    }

}
