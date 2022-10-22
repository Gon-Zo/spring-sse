package com.example.app.domain.user;

import com.example.app.constract.RegistrationType;
import com.example.app.domain.base.BaseTimeEntity;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private RegistrationType type;

    private String plaform;

    private String store;

    private String country;

    private String ip;

    private String channel;

    private String referer;

    private String landingUrl;

    private Boolean isAgreedMarketingEmail;

    private String verificationToken;

    private LocalDateTime enteredDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
