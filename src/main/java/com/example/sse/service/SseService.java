package com.example.sse.service;

import com.example.sse.doamin.DataSet;
import com.example.sse.repository.DataSetRepository;
import com.example.sse.web.dto.SseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Executor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SseService {

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
}
