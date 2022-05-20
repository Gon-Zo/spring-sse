package com.example.springcore.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LoggingAspect {

  @Around("within(com.example.springcore.web.*)")
  public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
    long begin = System.currentTimeMillis();
    Object reVal = pjp.proceed();
    System.out.println(System.currentTimeMillis() - begin);
    return reVal;
  }

  @Around("@annotation(Logging)")
  public Object logging(final ProceedingJoinPoint pjp) {
    try {
      log.info("LoggingAspect Success");
      return pjp.proceed(pjp.getArgs());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
