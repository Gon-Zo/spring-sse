package com.example.forkjoinpool.web;

import com.example.forkjoinpool.service.ForkJoinService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ForkJoinPollResource.class)
class ForkJoinPollResourceTest {

  private MockMvc mockMvc;

  @MockBean private ForkJoinService forkJoinService;

  @BeforeEach
  void setMockMvc() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new ForkJoinPollResource(forkJoinService))
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
  }

  @Test
  @DisplayName("fork join pool test case")
  void forkjoinpoolTaskTest() throws Exception {

    Integer num = 100;

    BDDMockito.given(forkJoinService.getForkJoinTask()).willReturn(num);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/api/fork-join-pool/task")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(forkJoinService).should().getForkJoinTask();

    action.andExpect(status().isOk());
  }
}
