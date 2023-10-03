package com.coder2195.password;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class CommandProcessor {
  private Game game;

  CommandProcessor(Game game) {
    this.game = game;
  }

  public boolean processCommand(String[] command) {
    try {
      Commands commandEnum = Commands.valueOf(command[0].toUpperCase());

      if (commandEnum == Commands.HELP) {
       System.out.println(Ansi.colorize("Commands:", Attribute.BOLD(), Attribute.BRIGHT_YELLOW_TEXT()));
       System.out.println(Ansi.colorize("This command lists all available commands:", Attribute.DIM()));
        for (Commands c : Commands.values()) {
         System.out.println( "/" + c.toString().toLowerCase());
        }
      } else if (commandEnum == Commands.EXIT) {
        System.out.print(Ansi.colorize("Are you sure you want to exit? (y/n) ", Attribute.BOLD(), Attribute.BRIGHT_RED_TEXT()));
        String confirm = game.getSc().nextLine();
        if (confirm.equalsIgnoreCase("y")) {
          System.out.println(Ansi.colorize("Goodbye!", Attribute.BOLD(), Attribute.BRIGHT_GREEN_TEXT()));
          System.exit(0);
          return true;
        } else {
          System.out.println(Ansi.colorize("Cancelled.", Attribute.BOLD(), Attribute.BRIGHT_GREEN_TEXT()));
        } 
      } else if (commandEnum == Commands.STATS) {
        if (command.length < 2) {
          System.out.println(Ansi.colorize("Available subcommands:", Attribute.BOLD(), Attribute.BRIGHT_YELLOW_TEXT()));
          System.out.println("/stats toggle\n/stats on\n/stats off");
        } else {
          if (command[1].equalsIgnoreCase("toggle")) {
            if (game.getStats() == null) {
              System.out.println("Stats are now on!");
              game.setStats(new Stats(game));
            } else {
              game.setStats(null);
              System.out.println("Stats are now off!");
            }
           
          } else if (command[1].equalsIgnoreCase("on")) {
            if (game.getStats() != null) {
              System.out.println("Stats are already on!");
            } else {
              game.setStats(new Stats(game));
              System.out.println("Stats are now on!");
            }

          } else if (command[1].equalsIgnoreCase("off")) {
            if (game.getStats() == null) {
              System.out.println("Stats are already off!");
            } else {
              game.setStats(null);
              System.out.println("Stats are now off!");
            }
          } else {
            System.out.println("Invalid /stats subcommand: " + command[1]);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Invalid command: /" + command[0] );
      return false;
    }


    return true;
  }

  public boolean processCommand(String command) {
    return processCommand(command.substring(1).split(" "));
  }
  
  
}
