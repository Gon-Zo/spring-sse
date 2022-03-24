package com.example.demo.repository;

import com.example.demo.config.QueryDslConfiguration;
import com.example.demo.domain.Division;
import com.example.demo.mock.DivisionMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(QueryDslConfiguration.class)
class DivisionRepositoryTest {

  @Autowired private DivisionRepository divisionRepository;

  private List<Division> divisions;

  @Nested
  class Select {

    @BeforeEach
    void init() {
      divisions = divisionRepository.saveAll(DivisionMock.createMasterDivision());

      divisionRepository.flush();
    }

    @Test
    void findByCode() {

      Division mock = divisions.get(0);

      Optional<Division> entityOptional = divisionRepository.findById(mock.getCode());

      org.junit.jupiter.api.Assertions.assertTrue(entityOptional.isPresent());

      Division entity = entityOptional.get();

      Assertions.assertThat(entity).isEqualTo(mock);

      org.junit.jupiter.api.Assertions.assertEquals(entity.getCode(), mock.getCode());
      org.junit.jupiter.api.Assertions.assertEquals(entity.getName(), mock.getName());
    }
  }
}
