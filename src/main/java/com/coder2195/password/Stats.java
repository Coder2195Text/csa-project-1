package com.coder2195.password;

import java.util.Arrays;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Stats {
  private Game game;

  public Stats(Game game) {
    this.game = game;
  }

  public String toString() {
    if (ChallengeList.descriptions.length >= Integer.MAX_VALUE) {
      System.out.println("I don't know how you have this many challenges but screw you."); 
      return "";
    }

    StringBuilder sb = new StringBuilder();
    sb.append(Ansi.colorize("Game Stats\n", Attribute.BOLD(), Attribute.YELLOW_TEXT(), Attribute.UNDERLINE()));
    double solved = 0;
    for (boolean item: game.getErrors()) {
      if (!item) {
        solved++;
      }
    }

    double solvedPercent = solved / game.getErrors().length * 100;

    

    sb.append(solvedPercent + "% (" + Math.abs(Math.round((int) solved)) + ") of your current challenges was solved.\n");
    sb.append("You have completed " + ((int) solved) + " of the total " + ChallengeList.descriptions.length + " challenges.\n");
    return sb.toString();
  }
}
