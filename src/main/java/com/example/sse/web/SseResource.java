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

    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();

    private final SseService sseService;

    @GetMapping(value = "/resource-uri")
    public SseEmitter handle() {
        return sseService.baseEvent();
    }

    @GetMapping(value = "fetch-dataset")
    public SseEmitter fetchDataSet() {
        return sseService.fetchDataSet();
    }

    @GetMapping("/api/subscribe")
    public SseEmitter subscribe(String id) {
        SseEmitter emitter = new SseEmitter();
        CLIENTS.put(id, emitter);

        emitter.onTimeout(() -> CLIENTS.remove(id));
        emitter.onCompletion(() -> CLIENTS.remove(id));
        return emitter;
    }

    @GetMapping("/api/publish")
    public void publish(String message) {
        Set<String> deadIds = new HashSet<>();

        CLIENTS.forEach((id, emitter) -> {
            try {
                log.info("message :: {}", message);
                emitter.send(message, MediaType.APPLICATION_JSON);
            } catch (Exception e) {
                deadIds.add(id);
                log.warn("disconnected id : {}", id);
            }
        });

        deadIds.forEach(CLIENTS::remove);
    }

}
