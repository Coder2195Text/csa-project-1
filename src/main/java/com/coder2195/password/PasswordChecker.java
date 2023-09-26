package com.coder2195.password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PasswordChecker {
  private String password;
  private boolean completed = false;
  private int currentChallenge = 0;
  private static Challenge[] challenges = {
      ChallengeList::challenge1
  };
  private static String[] descriptions = {
      ChallengeList.description1
  };
  
  private boolean[] errors;


  public PasswordChecker(String password) {
    setPassword(password);
  }

  public PasswordChecker() {
    this("");
  }

  public void setPassword(String password) {
    
    checkErrors();
    this.password = password;

  }

  private void checkErrors() {
    boolean hasErrors = false;
    
    while (!hasErrors) {
      errors = new boolean[challenges.length];
      for (int i = 0; i < challenges.length; i++) {
        errors[i] = challenges[i].execute(password);
        if (errors[i]) {
          hasErrors = true;
        }
        
      }

      if (!hasErrors) {
        currentChallenge++;
        if (currentChallenge >= challenges.length) {
          completed = true;
          break;
        }
      };
    }
  }

  public boolean[] getErrors() {
    return errors;
  }

  public int getCurrentChallenge() {
    return currentChallenge;
  }
}
