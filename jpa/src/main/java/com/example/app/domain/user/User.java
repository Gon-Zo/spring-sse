package com.example.app.domain.user;

import com.example.app.constract.StatusType;
import com.example.app.domain.base.BaseTimeEntity;
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

    @OneToOne(mappedBy = "user")
    private UserPassword userPassword;

    @OneToOne(mappedBy = "user")
    private UserMeta userMeta;

    @OneToOne(mappedBy = "user")
    private UserToken userToken;

    @OneToOne(mappedBy = "user")
    private UserCertification userCertification;

    @OneToOne(mappedBy = "user")
    private UserRegistration userRegistration;

    @OneToOne(mappedBy = "user")
    private UserDevice userDevice;

    @OneToOne(mappedBy = "user")
    private UserAgreement userAgreement;

    @OneToOne(mappedBy = "user")
    private UserSocial userSocial;
}
