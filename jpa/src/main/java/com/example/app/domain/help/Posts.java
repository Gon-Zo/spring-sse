package com.example.app.domain.help;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @JsonIgnore
    @OneToMany(mappedBy = "posts" , fetch = FetchType.LAZY)
    private Set<PostGroupRel> postGroupRelSet = new HashSet<>();
}
