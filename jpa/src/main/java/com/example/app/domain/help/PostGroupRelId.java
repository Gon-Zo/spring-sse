package com.example.app.domain.help;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
public class PostGroupRelId implements Serializable {

    @Column(nullable = false, name = "posts_id")
    private Long postsId;

    @Column(nullable = false, name = "posts_group_id")
    private String postsGroupId;

    @Column(nullable = false, name = "local")
    private String local;
}
