package com.example.springweb.config.certified;

public interface Certified {

  boolean match(String string, String encodingStr);

  boolean isNotMatch(String string, String encodingStr);

  String encode(String string);
}
