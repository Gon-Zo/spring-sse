package com.example.app.domain.user;

import com.example.app.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAgreement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("사용자 동의 여부")
    private String agreementType;

    @Column(nullable = false)
    @Comment("동의 여부")
    private Boolean isAgreed;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
