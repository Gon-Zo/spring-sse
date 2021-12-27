package com.example.sse.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SseDTO {

    private Long id;

    private String name;

    private String title;

}
