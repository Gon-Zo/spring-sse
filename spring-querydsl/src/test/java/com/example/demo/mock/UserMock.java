package com.example.demo.mock;

import com.example.demo.domain.User;
import com.example.demo.enums.RoleEnum;

import java.util.List;

public class UserMock {

  public static User createUser() {
    return User.allBuilder()
        .id(1L)
        .email("test@naver.com")
        .password("123456")
        .role(RoleEnum.ROLE_ADMIN)
        .build();
  }

  public static User createOtherUser() {
    return User.allBuilder()
        .id(2L)
        .email("test1@naver.com")
        .password("12345")
        .role(RoleEnum.ROLE_ADMIN)
        .build();
  }

  public static User createUserEmptyRole() {
    return User.allBuilder().id(1L).email("test@naver.com").password("123456").build();
  }

  public static List<User> createUsers() {
    return List.of(createUser(), createOtherUser());
  }

  public static List<User> createAllUsers() {
    return List.of(createUser(), createOtherUser());
  }

  public static List<User> createOtherUsers() {
    return List.of(
        createOtherUser(),
        User.allBuilder()
            .id(3L)
            .email("test2@naver.com")
            .password("12345")
            .role(RoleEnum.ROLE_ADMIN)
            .build());
  }
}
