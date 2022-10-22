package com.example.app.domain.base;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class CreatedTimeEntity {

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdDate;
}
