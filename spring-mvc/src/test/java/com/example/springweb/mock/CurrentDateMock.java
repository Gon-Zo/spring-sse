package com.example.springweb.mock;

import com.example.springweb.domain.CurrentDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CurrentDateMock {

  private static final String DEFAULT_TIME_ZONE = "Asia/Seoul";

  private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  private static final String from = "2013-04-08 10:10:10";

  public static List<CurrentDate> getCurrentDateList() {

    SimpleDateFormat transFormat = new SimpleDateFormat(DATE_FORMAT);

    try {

      Date nowDate = transFormat.parse(from);

      long timeInMilliSeconds = nowDate.getTime();

      java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);

      return List.of(CurrentDate.allBuilder()
              .id(1L)
              .javaDate(nowDate)
              .sqlDate(sqlDate)
              .build());

    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static String changeFormatOf(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
    return sdf.format(date);
  }
}
