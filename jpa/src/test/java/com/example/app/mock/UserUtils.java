package com.example.app.mock;

import com.example.app.domain.user.User;
import com.example.app.domain.user.UserMeta;
import com.example.app.domain.user.UserPassword;
import com.example.app.domain.user.UserRegistration;

/**
 * Test 케이스를 작성을 하면서 발생되는 비지니스 로직
 */
public class UserUtils {

    /**
     * 다시 Mock 데이터를 말아준다..
     *
     * @param user
     * @param userPassword
     * @return
     */
    public static User newUser(User user, UserPassword userPassword, UserMeta userMeta, UserRegistration userRegistration) {

        return User.builder().email(user.getEmail())
                .userPassword(userPassword)
                .userMeta(userMeta)
                .userRegistration(userRegistration)
                .build();
    }
}
