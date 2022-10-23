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
    private RegistrationType type;

    @Comment("플랫폼")
    private String platform;

    @Comment("스토어")
    private String store;

    @Comment("나라")
    private String country;

    @Comment("아이피")
    private String ip;

    @Comment("어디서 왔는지 채널명을 기록 쿠키에서 읽음")
    private String channel;

    @Comment("어디서 왔는지 referer url 기록")
    private String referer;

    @Comment("처음 도달한 페이지 path")
    private String landingUrl;

    @Comment("마케팅 이메일 수신 동의 여부")
    private Boolean isAgreedMarketingEmail;

    @Comment("인증 토큰")
    private String verificationToken;

    @Comment("넘어간 시간..?")
    private LocalDateTime enteredDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
