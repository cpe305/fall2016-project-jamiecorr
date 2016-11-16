package edu.calpoly.cpe305;

import java.math.BigDecimal;
import java.util.Scanner;

public class CasinoDriver {
  static final BigDecimal BALANCE = new BigDecimal(100);
  static final int CHOICE1 = 1;
  static final int CHOICE2 = 2;
  static Bank playersBank = new Bank(BALANCE);
  static String gameChoice;
  static String input = "yes";
  static Scanner scan;

  public static final void main(String[] args) {
    
    private static final Logger LOGGER = Logger.getLogger(Logger.class.getName());
    
    scan = new Scanner(System.in);

    while (input.equals("yes")) {
      LOGGER.log( Level.FINE, "You have ${0}" + playersBank.getCurrentBalance() + "  Press 1:Blackjack or 2:Poker");
      gameChoice = scan.nextLine();

      if (gameChoice.equals("1")) {
        Blackjack.play();
      } else if (gameChoice.equals("2")) {
        Poker.play(playersBank);
      }

      if (!playersBank.isBroke()) {
        LOGGER.log( Level.FINE,"Do you want to stay in the casino?");
        input = scan.nextLine();
      } else {
        LOGGER.log( Level.FINE,"No money left, now exiting casino.");
        break;
      }
    }

    LOGGER.log("---exit---");
    scan.close();
  }
}
