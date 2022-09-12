package com.example.springweb.web;

import com.example.springweb.domain.db1.CurrentDate;
import com.example.springweb.mock.CurrentDateMock;
import com.example.springweb.repository.db1.CurrentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StartResource.class)
class StartResourceTest {

  private MockMvc mockMvc;

  @MockBean private CurrentRepository currentRepository;

  @BeforeEach
  void setMockMvc() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new StartResource(currentRepository))
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
  }

  @Test
  @DisplayName("포멧 테스트")
  void getNowDate() throws Exception {

    // given
    List<CurrentDate> list = CurrentDateMock.getCurrentDateList();

    BDDMockito.given(currentRepository.findAll()).willReturn(list);

    // when
    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/start")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    // then

    // check logic
    BDDMockito.then(currentRepository).should().findAll();

    // check result
    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
        .andExpect(
            jsonPath("$[0].javaDate")
                .value(CurrentDateMock.changeFormatOf(list.get(0).getJavaDate())))
        .andExpect(jsonPath("$[0]").exists());
  }
}
