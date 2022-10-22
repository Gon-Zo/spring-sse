package com.example.springtransaction.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"itemId", "userId"})
public class ItemUserKey implements Serializable {

    private Long itemId;

    private Long userId;
}
