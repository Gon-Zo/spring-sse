package com.example.springweb.config.certified;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Sha256 implements Certified {

  @Override
  public boolean match(String string, String encodingStr) {
    return encode(string).equals(encodingStr);
  }

  @Override
  public boolean isNotMatch(String string, String encodingStr) {
    return !match(string, encodingStr);
  }

  @Override
  public String encode(String string) {
    String SHA = null;
    try {
      MessageDigest sh = MessageDigest.getInstance("SHA-256");
      sh.update(string.getBytes());
      byte[] byteData = sh.digest();
      StringBuilder sb = new StringBuilder();
      for (byte byteDatum : byteData) {
        sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
      }
      SHA = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return SHA;
  }
}
