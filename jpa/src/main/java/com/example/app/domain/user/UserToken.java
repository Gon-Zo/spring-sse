package com.example.app.domain.user;

import com.example.app.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String token;

    private LocalDateTime expiredDate;
    private LocalDateTime deletedDate;

    public void deletedToken(LocalDateTime deletedDate) {
        this.deletedDate = Optional.ofNullable(deletedDate).orElse(LocalDateTime.now());
    }
}
