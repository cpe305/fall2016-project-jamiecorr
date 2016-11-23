package edu.calpoly.cpe305;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasinoDriver {
  static final BigDecimal BALANCE = BigDecimal.valueOf(100);
  static final int PLAY_BJ = 1;
  static final int PLAY_POKER = 2;
  static Bank playersBank = new Bank(BALANCE);
  static String gameChoice;
  static String input = "yes";
  static Scanner scan;
  private static final Logger LOGGER = Logger.getLogger(Logger.class.getName());

  public static final void main(String[] args) {
    scan = new Scanner(System.in);
    
    GameFactory gameFactory = new GameFactory();
    Deck bDeck = gameFactory.getDeck("BLACKJACK");
    bDeck.createDeck();
    Deck pDeck = gameFactory.getDeck("POKER");
    pDeck.createDeck();

    while ("yes".equals(input)) {
      System.out.println("You have $" + playersBank.getCurrentBalance() + "  Press 1:Blackjack or 2:Poker");
      gameChoice = scan.nextLine();

      if ("1".equals(gameChoice)) {
        Blackjack.play();
      } else if ("2".equals(gameChoice)) {
        Poker.play(playersBank);
      }

      if (!playersBank.isBroke()) {
        System.out.println("Do you want to stay in the casino?");
        input = scan.nextLine();
      } else {
        System.out.println("No money left, now exiting casino.");
        break;
      }
    }

    System.out.println("---exit---");
    scan.close();
  }
}
