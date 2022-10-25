package com.example.app.domain.help;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Posts {

    @EmbeddedId
    private PostsId id;

    @Column(nullable = false)
    private Long parent;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private Long author;

    @Column(nullable = false)
    private String authorType;
}
