package com.example.app.domain.help;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PostGroupRel {

    @EmbeddedId
    private PostGroupRelId id;

    private String content;

    @JsonIgnore
    @MapsId(value = "posts_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "local", referencedColumnName = "local"),
            @JoinColumn(name = "posts_id", referencedColumnName = "id")
    })
    private Posts posts;
}
