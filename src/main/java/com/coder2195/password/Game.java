package com.coder2195.password;

import java.util.Scanner;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Game {
  private GameState gameState;
  private Stats stats;
  private boolean running;

  private PasswordChecker passwordChecker;
  private Scanner sc;


  private CommandProcessor commandProcessor;

  public Game() {
    gameState = GameState.MENU;
    running = true;
    sc = new Scanner(System.in);
    commandProcessor = new CommandProcessor(this);
    stats = new Stats(this);
  }

  public boolean isRunning() {
    return running;
  }

  public Scanner getSc() {
    return sc;
  }

  public Stats getStats() {
    return stats;
  }

  public boolean[] getErrors() {
    return passwordChecker.getErrors();
  }

  public void setStats(Stats stats) {
    this.stats = stats;
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

    System.out.print(stats != null ? stats.toString() : "");

    for (int i = 0; i <= currentChallenge; i++) {
      if (!errors[i]) {
        if (!hasCompleted) {
          System.out.println(Ansi.colorize("Completed:", Attribute.BRIGHT_GREEN_TEXT(), Attribute.GREEN_BACK(), Attribute.BOLD()));
          hasCompleted = true;
        }
        System.out.println(Ansi.colorize((i+1) + ". " + ChallengeList.descriptions[i], Attribute.GREEN_TEXT()));
      }
    }
    System.out.println(Ansi.colorize("Errors:", Attribute.BRIGHT_RED_TEXT(), Attribute.RED_BACK(), Attribute.BOLD()));
    for (int i = 0; i <= currentChallenge; i+=1) {
      if (errors[i]) {
        System.out.println(Ansi.colorize((i+1) + ". "  + ChallengeList.descriptions[i], Attribute.RED_TEXT()));
      }
    }

    if (!password.isBlank()) System.out.println(Ansi.colorize("Your previous password: " + password, Attribute.YELLOW_TEXT()));
    System.out.print(Ansi.colorize("Input password or a command (/help for list): ", Attribute.YELLOW_TEXT()));

    String line = sc.nextLine();

    if (line.startsWith("/")) {
      boolean success = commandProcessor.processCommand(line);

      System.out.print(Ansi.colorize("Press enter to continue... ", Attribute.WHITE_TEXT(), Attribute.DIM()));
      sc.nextLine();
    } else {
      passwordChecker.setPassword(line);
    }
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