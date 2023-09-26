package com.coder2195.password;

public class ChallengeList {
  public static String description1 = "Password must be at least 8 characters long";
  public static boolean challenge1(String password) {
    return password.length() >= 8;
  }
}
