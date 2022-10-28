package com.example.app.domain.user;

import com.example.app.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("토큰값")
    private String token;

    @Comment("만료일")
    private LocalDateTime expiredDate;
    @Comment("삭제일")
    private LocalDateTime deletedDate;

    @Transient
    public void deletedToken(LocalDateTime deletedDate) {
        this.deletedDate = Optional.ofNullable(deletedDate).orElse(LocalDateTime.now());
    }

    @Transient
    public static UserToken newUserToken(String token, User user, LocalDateTime expiredDate) {
        return new UserToken(token, user, expiredDate);
    }

    private UserToken(String token, User user, LocalDateTime expiredDate) {
        this.token = token;
        this.user = user;
        this.expiredDate = expiredDate;
    }
}
