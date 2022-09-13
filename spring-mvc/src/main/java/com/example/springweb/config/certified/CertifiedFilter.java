package com.example.springweb.config.certified;

import com.example.springweb.config.certified.exception.EmptyEncodeValueException;
import com.example.springweb.config.certified.exception.NotMatchEncodeValueException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CertifiedFilter extends OncePerRequestFilter {

  private final String X_CUSTOM_TOKEN = "X-CUSTOM-TOKEN";

  private final Set<String> skipUrls = Set.of("/api/**" );

  private final AntPathMatcher matcher = new AntPathMatcher();

  private final Certified certified;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String string = request.getParameter("name");

    String encodeString = request.getHeader(X_CUSTOM_TOKEN);

    // 받는 데이터가 둘중에 하나라도 비었을 경우 Exception 처리
    if (StringUtils.isBlank(string) || StringUtils.isBlank(encodeString)) {
      throw new EmptyEncodeValueException();
    }

    // 받은 토큰 값이 같지 않을 경우
    if (certified.isNotMatch(string, encodeString)) {
      throw new NotMatchEncodeValueException();
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return skipUrls.stream().anyMatch(path -> matcher.match(path, request.getServletPath()));
  }
}
