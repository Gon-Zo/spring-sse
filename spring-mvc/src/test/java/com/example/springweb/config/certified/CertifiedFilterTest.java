package com.example.springweb.config.certified;

import com.example.springweb.config.certified.exception.EmptyEncodeValueException;
import com.example.springweb.config.certified.exception.NotMatchEncodeValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootTest
class CertifiedFilterTest {

  @Autowired private CertifiedFilter filter;

  @Autowired private Certified certified;

  private MockHttpServletRequest req;

  private MockHttpServletResponse res;

  private MockFilterChain chain;

  private final String X_CUSTOM_TOKEN = "X-CUSTOM-TOKEN";

  private final String name = "tester";

  private String header;

  @BeforeEach
  void init() {
    this.req = new MockHttpServletRequest();
    this.res = new MockHttpServletResponse();
    this.chain = new MockFilterChain();
    this.header = certified.encode(name);
  }

  @Test
  void checked_EmptyEncodeValueException() throws ServletException, IOException {
    req.setRequestURI("/test");

    Assertions.assertThrows(
        EmptyEncodeValueException.class, () -> filter.doFilter(req, res, chain));
  }

  @Test
  void checked_NotMatchEncodeValueException() throws ServletException, IOException {
    req.setRequestURI("/test");

    req.setParameter("name", name);

    req.addHeader(X_CUSTOM_TOKEN, "xxx");

    Assertions.assertThrows(
        NotMatchEncodeValueException.class, () -> filter.doFilter(req, res, chain));
  }

  @Test
  void checked_success() throws ServletException, IOException {

    req.setRequestURI("/test");

    req.setParameter("name", name);

    req.addHeader(X_CUSTOM_TOKEN, header);

    filter.doFilter(req, res, chain);
  }
}
