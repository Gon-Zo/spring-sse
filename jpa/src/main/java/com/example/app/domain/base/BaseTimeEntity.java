package com.example.app.domain.base;

import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity extends CreatedTimeEntity {

    @UpdateTimestamp
    @Comment("수정일")
    private LocalDateTime updatedDate;
}
