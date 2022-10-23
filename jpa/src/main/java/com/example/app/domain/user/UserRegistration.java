package com.example.app.domain.user;

import com.example.app.constract.RegistrationType;
import com.example.app.domain.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegistration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("가입 유형")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationType type;

    @Comment("플랫폼")
    @Column(nullable = false)
    private String platform;

    @Comment("스토어")
    @Column(nullable = false)
    private String store;

    @Comment("나라")
    @Column(nullable = false)
    private String country;

    @Comment("아이피")
    @Column(nullable = false)
    private String ip;

    @Comment("어디서 왔는지 채널명을 기록 쿠키에서 읽음")
    @Column(nullable = false)
    private String channel;

    @Comment("어디서 왔는지 referer url 기록")
    @Column(nullable = false)
    private String referer;

    @Comment("처음 도달한 페이지 path")
    @Column(nullable = false)
    private String landingUrl;

    @Comment("마케팅 이메일 수신 동의 여부")
    @Column(nullable = false)
    private Boolean isAgreedMarketingEmail;

    @Comment("인증 토큰")
    @Column(nullable = false)
    private String verificationToken;

    @Comment("넘어간 시간..?")
    @Column(nullable = false)
    private LocalDateTime enteredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
