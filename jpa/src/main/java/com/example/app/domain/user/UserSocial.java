package com.example.app.domain.user;

import com.example.app.constract.Gender;
import com.example.app.constract.SocialType;
import com.example.app.domain.base.BaseTimeEntity;
import com.example.app.domain.user.extend.Birthday;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSocial extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType type;

    @OneToOne
    @JoinColumn(name = "provider_user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String secret;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String profilePath;

    @Column(nullable = false)
    private String age;

    @Embedded
    private Birthday birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isConnected;

    private LocalDateTime connectedDate;
}
