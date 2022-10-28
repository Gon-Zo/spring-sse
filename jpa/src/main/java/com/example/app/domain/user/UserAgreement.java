package com.example.app.domain.user;

import com.example.app.domain.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Optional;

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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    void insert() {
        this.isAgreed = Optional.ofNullable(this.isAgreed).orElse(Boolean.FALSE);
        this.agreementType = Optional.ofNullable(this.agreementType).orElse("NONE");
    }

    @Transient
    public static UserAgreement newUserAgreement(String agreementType, Boolean isAgreed) {
        return new UserAgreement(agreementType, isAgreed);
    }

    private UserAgreement(String agreementType, Boolean isAgreed) {
        this.agreementType = agreementType;
        this.isAgreed = isAgreed;
    }
}
