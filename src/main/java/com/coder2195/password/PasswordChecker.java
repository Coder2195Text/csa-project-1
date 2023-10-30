package com.coder2195.password;

public class PasswordChecker {
  private String password;
  private boolean completed = false;
  private int currentChallenge = 0;
  private static final Challenge[] challenges = {
      ChallengeList::challenge1,
      ChallengeList::challenge2,
      ChallengeList::challenge3,
      ChallengeList::challenge4,
      ChallengeList::challenge5,
      ChallengeList::challenge6,
      ChallengeList::challenge7,
      ChallengeList::challenge8,
      ChallengeList::challenge9,
      ChallengeList::challenge10,
      ChallengeList::challenge11,
      ChallengeList::challenge12,
      ChallengeList::challenge13,
      ChallengeList::challenge14,
      ChallengeList::challenge15,
      ChallengeList::challenge16,
      ChallengeList::challenge17,
      ChallengeList::challenge18
  };

  private boolean[] errors;

  public PasswordChecker(String password) {
    setPassword(password);
  }

  public PasswordChecker() {
    this("");
  }

  public void setPassword(String password) {
    this.password = password;
    checkErrors();
  }

  public boolean isCompleted() {
    return completed;
  }

  public String getPassword() {
    return password;
  }

  private void checkErrors() {
    boolean hasErrors = false;
    errors = new boolean[currentChallenge + 1];
    for (int i = currentChallenge; i >= 0; i--) {
      errors[i] = !challenges[i].execute(password);
      if (errors[i]) {
        hasErrors = true;
      }
    }

    while (!hasErrors) {
      if (++currentChallenge >= challenges.length) {
        completed = true;
        return;
      }
      if (!challenges[currentChallenge].execute(password)) {
        errors = new boolean[currentChallenge + 1];
        errors[currentChallenge] = true;
        hasErrors = true;
      }
    }
  }

  public boolean[] getErrors() {
    return errors;
  }

  public int getCurrentChallenge() {
    return currentChallenge;
  }
}
