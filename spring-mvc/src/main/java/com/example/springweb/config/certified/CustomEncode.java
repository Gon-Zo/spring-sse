package com.example.springweb.config.certified;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Profile("custom")
@Component("certified")
public class CustomEncode extends Sha512 {

  @Override
  public String encode(String string) {

    Date date = new Date();

    SimpleDateFormat DateFor = new SimpleDateFormat("yyyyMMdd");

    String stringDate = DateFor.format(date);

    return super.encode(string + stringDate);
  }
}
