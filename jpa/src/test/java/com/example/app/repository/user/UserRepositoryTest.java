package com.example.app.repository.user;

import com.example.app.config.TestRepositoryConfig;
import com.example.app.constract.StatusType;
import com.example.app.domain.user.User;
import com.example.app.domain.user.UserMeta;
import com.example.app.domain.user.UserPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.app.mock.MockUtil.readJoin;
import static com.example.app.mock.UserUtils.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = {TestRepositoryConfig.class})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @DisplayName("회원가입 로직")
    void save() {

        // given
        User mock = getUser();

        // when
        User entity = userRepository.save(mock);

        // user
        assertNotNull(entity.getId());
        assertEquals(entity.getEmail(), mock.getEmail());
        assertEquals(entity.getStatus(), StatusType.JOIN);
        assertNotNull(entity.getCreatedDate());
        assertNotNull(entity.getUpdatedDate());

        //user password
        assertNotNull(entity.getUserPassword().getId());
        assertEquals(entity.getUserPassword().getPassword(), mock.getUserPassword().getPassword());
        assertNotNull(entity.getUserPassword().getCreatedDate());
        assertNotNull(entity.getUserPassword().getUpdatedDate());


        assertNotNull(entity.getUserMeta().getId());
        assertEquals(entity.getUserMeta().getBirthday(), mock.getUserMeta().getBirthday());
        assertNotNull(entity.getUserMeta().getCreatedDate());
        assertNotNull(entity.getUserMeta().getUpdatedDate());
    }

    private User getUser() {

        UserMeta userMeta = readJoin("json/user_meta/save_1.json", UserMeta.class);

        UserPassword userPasswordMock = readJoin("json/user_password/save_1.json", UserPassword.class);

        User mock = readJoin("json/user/save_1.json", User.class);

        return newUser(mock, userPasswordMock, userMeta);
    }
}