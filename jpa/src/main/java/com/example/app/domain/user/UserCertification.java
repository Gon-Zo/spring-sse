package com.example.app.domain.user;

import com.example.app.constract.Gender;
import com.example.app.domain.base.BaseTimeEntity;
import com.example.app.domain.user.extend.Birthday;
import lombok.*;

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

    private String country;

    private Boolean adult;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Birthday birthday;

    private String di;

    private String ip;

    private Boolean isSuccess;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
