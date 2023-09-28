package com.coder2195.password;

import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Game {
  private GameState gameState;
  private boolean running;

  private PasswordChecker passwordChecker;
  private Scanner sc;

  public Game() {
    gameState = GameState.MENU;
    running = true;
    sc = new Scanner(System.in);
  }



  public boolean isRunning() {
    return running;
  }

  private void clear() {
    try {
      Thread.sleep(100);
    } catch (Exception e) {
    };
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void menuFrame() {
    for (String line : Constants.TITLE_TEXT) {
        System.out.println(line);
      }
      System.out.println(Ansi.colorize("Paste in your previous password, or press enter to play...",
          Attribute.RAPID_BLINK(), Attribute.YELLOW_TEXT()));
      
      String line;
      if ((line = sc.nextLine()).isEmpty()) {
        passwordChecker = new PasswordChecker();
      } else {

        passwordChecker = new PasswordChecker(line);
      }

      gameState = GameState.PLAYING;
  }

  private void playingFrame() {
    int currentChallenge = passwordChecker.getCurrentChallenge();
    boolean[] errors = passwordChecker.getErrors();
    String password = passwordChecker.getPassword();
    boolean completed = passwordChecker.isCompleted();
    boolean hasCompleted = false;

    if (completed) {
      gameState = GameState.WIN;
      return;
    }

    for (int i = 0; i <= currentChallenge; i++) {
      if (!errors[i]) {
        if (!hasCompleted) {
          System.out.println(Ansi.colorize("Completed:", Attribute.BRIGHT_GREEN_TEXT(), Attribute.GREEN_BACK(), Attribute.BOLD()));
          hasCompleted = true;
        }
        System.out.println(Ansi.colorize((i+1) + "." + ChallengeList.descriptions[i], Attribute.GREEN_TEXT()));
      }
    }
    System.out.println(Ansi.colorize("Errors:", Attribute.BRIGHT_RED_TEXT(), Attribute.RED_BACK(), Attribute.BOLD()));
    for (int i = 0; i <= currentChallenge; i++) {
      if (errors[i]) {
        System.out.println(Ansi.colorize((i+1) + "."  + ChallengeList.descriptions[i], Attribute.RED_TEXT()));
      }
    }

    if (!password.isBlank()) System.out.println(Ansi.colorize("Your previous password: " + password, Attribute.YELLOW_TEXT()));

    String line = sc.nextLine();
    passwordChecker.setPassword(line);
  }

  public void frame() {
    // clear screen
    clear();

    if (gameState == GameState.MENU) {
      menuFrame();
    } else if (gameState == GameState.PLAYING) {
      playingFrame();
    } else if (gameState == GameState.WIN) {
      System.out.println("You win!!!");
    } else {
      System.out.println("Error");
    }
  }
}