package com.example.app.domain.user;

import com.example.app.constract.StatusType;
import com.example.app.domain.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StreamUtils;

import javax.persistence.*;
import java.util.Optional;

@DynamicInsert
@DynamicUpdate
@Builder
@Getter
@Table
@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자 계정명")
    @Column(nullable = false, unique = true)
    private String email;

    @Comment("사용자 상태")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Comment("이메일 인증 여부")
    @Column(name = "email_verified_yn", nullable = false)
    private Boolean isEmailVerified;

    @Comment("계스트 계정명")
    private String guestName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserPassword userPassword;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserMeta userMeta;

    // this action is login add token data
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserToken userToken;

    // this action is certification
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserCertification userCertification;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserRegistration userRegistration;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserDevice userDevice;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserAgreement userAgreement;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserSocial userSocial;

    @PrePersist
    void insert() {
        this.status = StringUtils.isNotEmpty(this.guestName) ? StatusType.GUEST : StatusType.JOIN;
        this.isEmailVerified = Optional.ofNullable(this.isEmailVerified).orElse(Boolean.FALSE);
    }
}
