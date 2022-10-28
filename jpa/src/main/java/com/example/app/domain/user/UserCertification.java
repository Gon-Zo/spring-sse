package com.example.app.domain.user;

import com.example.app.constract.Gender;
import com.example.app.domain.base.BaseTimeEntity;
import com.example.app.domain.user.extend.Birthday;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Builder
@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCertification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("인증국가")
    @Column(nullable = false)
    private String country;

    @Comment("인증국가")
    @Column(nullable = false, name = "audlt_yn")
    private Boolean isAdult;

    @Comment("성별")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Embedded
    @Comment("생년월일")
    private Birthday birthday;

    @Comment("중복확인코드")
    @Column(nullable = false)
    private String di;

    @Comment("아이피")
    @Column(nullable = false)
    private String ip;

    @Comment("인증유무")
    @Column(nullable = false)
    private Boolean isSuccess;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
