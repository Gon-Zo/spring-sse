package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.projection.IUser;
import com.example.demo.mock.UserMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @BeforeEach
  void init() {}

  @Test
  void save() throws Exception {
    User mock = UserMock.createUser();

    User entity = userRepository.save(mock);

    Assertions.assertThat(mock).isEqualTo(entity);

    org.junit.jupiter.api.Assertions.assertEquals(mock.getId(), entity.getId());
    org.junit.jupiter.api.Assertions.assertEquals(mock.getEmail(), entity.getEmail());
    org.junit.jupiter.api.Assertions.assertEquals(mock.getPassword(), entity.getPassword());
    org.junit.jupiter.api.Assertions.assertEquals(mock.getRole(), entity.getRole());
  }

  @Test
  void save_empty_role() throws Exception {
    User mock = UserMock.createUserEmptyRole();
    org.junit.jupiter.api.Assertions.assertThrows(
        DataIntegrityViolationException.class, () -> userRepository.save(mock));
  }

  @Nested
  class Select {

    User mock;

    @BeforeEach
    void init() {
      // given
      mock = userRepository.save(UserMock.createUser());
    }

    @Test
    void findByEmail() throws Exception {

      Optional<User> entityOptional = userRepository.findByEmail(mock.getEmail());

      org.junit.jupiter.api.Assertions.assertTrue(entityOptional.isPresent());

      User entity = entityOptional.get();

      Assertions.assertThat(entity).isEqualTo(mock);
      org.junit.jupiter.api.Assertions.assertEquals(mock.getId(), entity.getId());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getEmail(), entity.getEmail());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getPassword(), entity.getPassword());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getRole(), entity.getRole());
    }

    @Test
    void findByEmail_projection() throws Exception {

      Optional<IUser> entityOptional = userRepository.findByEmail(mock.getEmail(), IUser.class);

      org.junit.jupiter.api.Assertions.assertTrue(entityOptional.isPresent());

      IUser entity = entityOptional.get();

      org.junit.jupiter.api.Assertions.assertEquals(mock.getId(), entity.getId());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getEmail(), entity.getEmail());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getPassword(), entity.getPassword());
      org.junit.jupiter.api.Assertions.assertEquals(mock.getRole(), entity.getRole());
    }

    @Test
    void existsByEmail() {

      boolean isExists = userRepository.existsByEmail(mock.getEmail());

      org.junit.jupiter.api.Assertions.assertTrue(isExists);
    }

    @Test
    void existsByEmail_false() {

      String email = "email@naver.com";

      boolean isExists = userRepository.existsByEmail(email);

      org.junit.jupiter.api.Assertions.assertFalse(isExists);
    }
  }
}
