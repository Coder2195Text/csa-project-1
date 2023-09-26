package com.coder2195.password;

import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Game {
  private GameState gameState;
  private boolean running;

  private PasswordChecker passwordChecker;

  public Game() {
    gameState = GameState.MENU;
    running = true;
  }

  public boolean isRunning() {
    return running;
  }

  public void frame() {
    // clear screen
    System.out.print("\033[H\033[2J");
    System.out.flush();

    if (gameState == GameState.MENU) {
      for (String line : Constants.TITLE_TEXT) {
        System.out.println(line);
      }
      System.out.println(Ansi.colorize("Paste in your previous password, or press enter to play...",
          Attribute.RAPID_BLINK(), Attribute.YELLOW_TEXT()));
      Scanner sc = new Scanner(System.in);

      if (!sc.hasNextLine()) {
        passwordChecker = new PasswordChecker();
      } else {
        passwordChecker = new PasswordChecker(sc.nextLine());
      }

      sc.close();

      gameState = GameState.PLAYING;
    } else if (gameState == GameState.PLAYING) {
      System.out.println("Playing");
    } else if (gameState == GameState.SAVE_PROGRESS) {
      System.out.println("Game Over");
    } else {
      System.out.println("Error");
    }

    try {
      Thread.sleep(100);
    } catch (Exception e) {
    }
    ;
  }
}