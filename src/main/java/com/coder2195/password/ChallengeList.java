package com.coder2195.password;

import java.util.Arrays;

import org.apache.commons.math3.primes.Primes;

import com.vdurmont.emoji.EmojiManager;

public class ChallengeList {
  public static final String[] descriptions = {
      "Must be at least 5 characters long.",
      "Must contain at least one uppercase letter.",
      "Must contain numbers.",
      "Must have a special character.",
      "Password must contain the instagram of Justin's club.",
      "Must contain a month of the year.",
      "Must contain a prime number.",
      "Must include the first name of the creator of this video: \"https://www.youtube.com/shorts/bZGethiDSo0\"",
      "Must name the language used to write this game.",
      "Must be at least 40 characters long",
      "Letters 10-13 must be: \"D34D\"",
      "First word of the name of this game: https://www.compart.com/en/unicode/U+0D9E",
      "Must contain the country that is guilty in the Russo-Ukrainian war.",
      "Must contain the name of this political idea: https://www.compart.com/en/unicode/U+262D",
      "What is a OH- ion called?",
      "Contains teacher's last name.",
      "Which borough should Rockaway REALLY be in?"
  };

  public static boolean challenge1(String password) {
    return password.length() >= 5;
  }

  public static boolean challenge2(String password) {
    return password.matches(".*[A-Z].*");
  }

  public static boolean challenge3(String password) {
    return password.matches(".*[0-9].*");
  }

  public static boolean challenge4(String password) {
    return password.matches(".*[^A-Za-z0-9].*");
  }

  public static boolean challenge5(String password) {
    return password.toLowerCase().matches(".*bths.repair.*");
  }

  public static boolean challenge6(String password) {
    return password.toLowerCase().matches(
        ".*january.*|.*february.*|.*march.*|.*april.*|.*may.*|.*june.*|.*july.*|.*august.*|.*september.*|.*october.*|.*november.*|.*december.*");

  }

  public static boolean challenge7(String password) {
    // get all numbers from string ie "D34D3" yields [34, 3]
    String[] numbers = password.split("[^0-9]+");
    // convert to int array
    int[] ints = Arrays.stream(numbers).filter(c -> !c.isBlank()).mapToInt(Integer::parseInt).toArray();
    // check if any are prime
    for (int i : ints) {
      if (Primes.isPrime(i))
        return true;
    }

    return false;
  }

  public static boolean challenge8(String password) {
    return password.toLowerCase().matches(".*steven.*");
  }

  public static boolean challenge9(String password) {
    return password.toLowerCase().matches(".*java.*");
  }

  public static boolean challenge10(String password) {
    return password.length() >= 40;
  }

  public static boolean challenge11(String password) {
    if (password.length() < 13)
      return false;
    String subString = password.substring(9, 13);
    return subString.equals("D34D");
  }

  public static boolean challenge12(String password) {
    return password.toLowerCase().matches(".*among.*");
  }

  public static boolean challenge13(String password) {
    return password.toLowerCase().matches(".*russia.*");
  }

  public static boolean challenge14(String password) {
    return password.toLowerCase().matches(".*communism.*");
  }

  public static boolean challenge15(String password) {
    return password.toLowerCase().matches(".*hydroxide.*");
  }

  public static boolean challenge16(String password) {
    return password.toLowerCase().matches(".*donald.*");
  }

  public static boolean challenge17(String password) {
    return password.toLowerCase().matches(".*holmer.*");
  }

  public static boolean challenge18(String password) {
    return password.toLowerCase().matches(".*brooklyn.*");
  }
}
