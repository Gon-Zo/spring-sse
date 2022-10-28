package com.example.app.service.dto;

import com.example.app.constract.Gender;
import com.example.app.constract.LoginType;
import com.example.app.constract.RegistrationType;
import com.example.app.constract.StatusType;
import com.example.app.domain.user.*;
import com.example.app.domain.user.extend.Birthday;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestDTO {

    private String email;

    private StatusType status;

    private Boolean isEmailVerified;

    private LoginType loginType;

    private String agreementType;

    private Boolean isAgreed;

    private Birthday birthday;

    private Gender gender;

    private String password;

    private RegistrationType registrationType;

    private String platform;

    private String store;

    private String country;

    private String ip;

    private String channel;

    private String referer;

    private String landingUrl;

    private Boolean isAgreedMarketingEmail;

    private String verificationToken;

    private LocalDateTime enteredDate;

    public User toEntity() {

        UserPassword userPassword = UserPassword.newUserPassword(this.password);

        UserMeta userMeta = UserMeta.newUserMeta(this.birthday, this.gender);

        UserRegistration userRegistration = UserRegistration.builder()
                .type(this.registrationType)
                .platform(this.platform)
                .store(this.store)
                .country(this.country)
                .ip(this.ip)
                .channel(this.channel)
                .referer(this.referer)
                .landingUrl(this.landingUrl)
                .isAgreedMarketingEmail(this.isAgreedMarketingEmail)
                .verificationToken(this.verificationToken)
                .enteredDate(this.enteredDate)
                .build();

        UserAgreement userAgreement = UserAgreement.newUserAgreement(this.agreementType, this.isAgreed);

        return User.builder()
                .email(this.email)
                .status(this.status)
                .isEmailVerified(this.isEmailVerified)
                .loginType(this.loginType)
                .userPassword(userPassword)
                .userMeta(userMeta)
                .userRegistration(userRegistration)
                .userAgreement(userAgreement)
                .build();
    }
}
