package com.coder2195.password;

public class ChallengeList {
  public static String description1 = "Password must be at least 8 characters long";
  public static boolean challenge1(String password) {
    return password.length() >= 8;
  }

  public static String description2 = "Password must contain at least one uppercase letter";
  public static boolean challenge2(String password) {
    return password.matches(".*[A-Z].*");
  }
}
