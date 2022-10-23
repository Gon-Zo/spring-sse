package com.example.app.domain.base;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class CreatedTimeEntity {

    @CreationTimestamp
    @Comment("생성일")
    private LocalDateTime createdDate;
}
