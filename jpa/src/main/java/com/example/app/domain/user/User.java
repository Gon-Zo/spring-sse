package com.example.app.domain.user;

import com.example.app.constract.StatusType;
import com.example.app.domain.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

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
        this.status = StatusType.JOIN;
    }
}
