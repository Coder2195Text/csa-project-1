package com.coder2195.password;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();

    while (game.isRunning()) {
      game.frame();
    }
  }
}