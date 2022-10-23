package com.example.app.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLatestState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("장소")
    @Column(nullable = false)
    private String locale;

    @Comment("성인 필터 활성화 여부")
    @Column(name = "adult_filter_yn", nullable = false)
    private Boolean isAdultFilter;

    @Comment("최근 유저 계정명 변경 일시")
    private LocalDateTime latestUserNameUpdatedDate;

    @Comment("패스워드 변경 알람일시")
    private LocalDateTime latestPasswordRemindedDate;

    @Comment("모든 기기 비활성화 일시")
    private LocalDateTime latestAllDeviceDeactivedDate;

    @Comment("사용자 정보 최근 수정 일시")
    private LocalDateTime latestUpdatedDate;

    @Comment("최근 방문일시")
    private LocalDateTime latestVisitedDate;
}
