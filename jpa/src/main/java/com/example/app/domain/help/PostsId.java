package com.example.app.domain.help;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PostsId implements Serializable {

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String local;
}
