package com.example.app.domain.user;

import com.example.app.constract.Gender;
import com.example.app.domain.base.BaseTimeEntity;
import com.example.app.domain.user.extend.Birthday;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Table
@Entity(name = "user_meta")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMeta extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    @Comment("생년월일")
    private Birthday birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("성별")
    private Gender gender;

    @PrePersist
    void prePersist() {
        this.gender = Optional.ofNullable(this.gender).orElse(Gender.NONE);
    }
}
