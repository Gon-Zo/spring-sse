package com.example.app.domain.user;

import com.example.app.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDevice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String platform;

    private String deviceCode;

    private Integer authCount;

    private Boolean isActivated;

    private LocalDateTime latestCheckedDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
