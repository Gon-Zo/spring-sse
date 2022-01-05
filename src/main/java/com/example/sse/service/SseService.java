package com.example.sse.service;

import com.example.sse.doamin.DataSet;
import com.example.sse.repository.DataSetRepository;
import com.example.sse.web.dto.SseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseService {

    private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();

    private static final Map<String, SseEmitter> GROUP_CAHNEL = new ConcurrentHashMap<>();

    private final DataSetRepository dataSetRepository;

    public SseEmitter baseEvent() {

        // data set
        SseDTO dataSet = SseDTO.builder()
                .id(1L)
                .title("test... 1")
                .name("test .. ")
                .build();

        // event builder
        SseEmitter.SseEventBuilder builder = SseEmitter.event();

        // emitter
        SseEmitter emitter = new SseEmitter();

        try {

            // sse data add dataset
            emitter.send(builder.data(dataSet)
                    .name("dataset-create")
                    .id(String.valueOf(dataSet.hashCode())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return emitter;
    }

    public SseEmitter fetchDataSet() {

        SseEmitter emitter = new SseEmitter();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {

            List<DataSet> dataSets = dataSetRepository.findAll();

            try {

                for (DataSet dataSet : dataSets) {

                    randomDelay();

                    emitter.send(dataSet);

                }

                emitter.complete();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        executor.shutdown();

        return emitter;
    }

    private void randomDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 그룹 체널을 만들어서 구독 한다?
     *
     * @param groupId
     * @return
     */
    public SseEmitter onSubscribeByGroup(String groupId) {

        log.info("구독 시작");

        SseEmitter emitter = new SseEmitter();

        GROUP_CAHNEL.put(groupId, emitter);

        emitter.onTimeout(() -> GROUP_CAHNEL.remove(groupId));

        emitter.onCompletion(() -> GROUP_CAHNEL.remove(groupId));

        return emitter;
    }

    public SseEmitter onSubscribe(String id) {
        SseEmitter emitter = new SseEmitter();
        CLIENTS.put(id, emitter);

        emitter.onTimeout(() -> CLIENTS.remove(id));
        emitter.onCompletion(() -> CLIENTS.remove(id));
        return emitter;
    }

    public void onPublish(String message) {
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
