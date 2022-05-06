package com.example.springweb.config.certified;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Sha512 implements Certified {

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
      MessageDigest messageDist = MessageDigest.getInstance("SHA-512");
      byte[] bateArray = messageDist.digest(string.getBytes());
      SHA = new String(Base64.encodeBase64(bateArray, false));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return SHA;
  }
}
