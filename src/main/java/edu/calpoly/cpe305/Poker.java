package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Poker {
  public static void play(Bank bank) {
    // use escape to get out of loops
    int escape = 0;

    System.out.println("New Hand?");
    String input = CasinoDriver.scan.nextLine();

    while (input.equals("yes")) {
      Deck currentDeck = new Deck("Jim");
      currentDeck.createDeck();

      PokerHandEvaluator user = new PokerHandEvaluator("Player");

      Card userCard1 = currentDeck.drawRandomCard();
      Card userCard2 = currentDeck.drawRandomCard();
      Card userCard3 = currentDeck.drawRandomCard();
      Card userCard4 = currentDeck.drawRandomCard();
      Card userCard5 = currentDeck.drawRandomCard();
      user.addCard(userCard1);
      user.addCard(userCard2);
      user.addCard(userCard3);
      user.addCard(userCard4);
      user.addCard(userCard5);

      System.out.println("How much do you want to bet this round?");
      BigDecimal bet = CasinoDriver.scan.nextBigDecimal();

      // order if increasing value
      user.organize();

      System.out.println("Player cards:  " + user);
      System.out.println("Do you want to stay with these cards?");
      String answer = CasinoDriver.scan.nextLine();

      if (answer.equals("no")) {
        System.out.println("Enter which cards you want to change (ex. 1,4,5)");
        String cards = CasinoDriver.scan.nextLine();
        user.reDeal(cards, currentDeck);
      } else {
        escape = 0;
      }

      user.organize();
      System.out.println(user);

      if (user.hasPair()) {
        CasinoDriver.playersBank.addMoney(bet);
        System.out.println("Won with a pair");
      } else if (user.hasTwoPairs()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(2)));
        System.out.println("Won with 2 pairs");
      } else if (user.hasTriple()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(3)));
        System.out.println("Won with 3 of a kind");
      } else if (user.hasStraight()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(4)));
        System.out.println("Won with a straight");
      } else if (user.hasFlush()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(6)));
        System.out.println("Won with a flush");
      } else if (user.hasFullHouse()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(9)));
        System.out.println("Won with a full house");
      } else if (user.hasQuad()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(25)));
        System.out.println("Won with four of a kind");
      } else if (user.hasStraightFlush()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(50)));
        System.out.println("Won with a straight flush");
      } else if (user.hasRoyalFlush()) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(250)));
        System.out.println("Daangg....royal flush");
      } else {
        CasinoDriver.playersBank.subtractMoney(bet);
        System.out.println("You lost");
      }

      System.out.println(CasinoDriver.playersBank);

      // need money to play
      if (CasinoDriver.playersBank.isBroke()) {
        System.out.println("No more money");
        break;
      }

      System.out.println("New hand?");
      input = CasinoDriver.scan.nextLine();
    }
  }
}
