package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Poker {
  public Poker() {
    play();
  }
  
  public void play() {
    System.out.println("New Hand?");
    String input = CasinoDriver.scan.nextLine();

    while ("yes".equals(input)) {
      Deck currentDeck = new Deck();
      currentDeck.createDeck();

      Hand playersHand = new Hand("Player");

      Card playersHandCard1 = currentDeck.drawRandomCard();
      Card playersHandCard2 = currentDeck.drawRandomCard();
      Card playersHandCard3 = currentDeck.drawRandomCard();
      Card playersHandCard4 = currentDeck.drawRandomCard();
      Card playersHandCard5 = currentDeck.drawRandomCard();
      playersHand.addCard(playersHandCard1);
      playersHand.addCard(playersHandCard2);
      playersHand.addCard(playersHandCard3);
      playersHand.addCard(playersHandCard4);
      playersHand.addCard(playersHandCard5);

      System.out.println("How much do you want to bet this round?");
      BigDecimal bet = CasinoDriver.scan.nextBigDecimal();

      // order if increasing value
      playersHand.organize();

      System.out.println("Player cards:  " + playersHand.printHand());
      System.out.println("Do you want to stay with these cards?");
      String answer = CasinoDriver.scan.nextLine();

      if ("no".equals(answer)) {
        System.out.println("Enter which cards you want to change (ex. 1,4,5)");
        String cards = CasinoDriver.scan.nextLine();
        playersHand.reDeal(cards, currentDeck);
      }
      
      playersHand.organize();
      System.out.println(playersHand.printHand());

      if (PokerHandEvaluator.hasPair(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet);
        System.out.println("Won with a pair");
      } else if (PokerHandEvaluator.hasTwoPairs(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(2)));
        System.out.println("Won with 2 pairs");
      } else if (PokerHandEvaluator.hasTriple(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(3)));
        System.out.println("Won with 3 of a kind");
      } else if (PokerHandEvaluator.hasStraight(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(4)));
        System.out.println("Won with a straight");
      } else if (PokerHandEvaluator.hasFlush(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(6)));
        System.out.println("Won with a flush");
      } else if (PokerHandEvaluator.hasFullHouse(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(9)));
        System.out.println("Won with a full house");
      } else if (PokerHandEvaluator.hasQuad(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(25)));
        System.out.println("Won with four of a kind");
      } else if (PokerHandEvaluator.hasStraightFlush(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(50)));
        System.out.println("Won with a straight flush");
      } else if (PokerHandEvaluator.hasRoyalFlush(playersHand)) {
        CasinoDriver.playersBank.addMoney(bet.multiply(BigDecimal.valueOf(250)));
        System.out.println("Daangg....royal flush");
      } else {
        CasinoDriver.playersBank.subtractMoney(bet);
        System.out.println("You lost");
      }

      System.out.println(CasinoDriver.playersBank.printCurrentBalance());

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
