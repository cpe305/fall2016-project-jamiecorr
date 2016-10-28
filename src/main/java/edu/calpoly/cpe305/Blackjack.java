package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Blackjack {
  static Hand playersHand;
  static Hand dealersHand;
  static boolean isStillPlaying = true;
  static BigDecimal bet;
  static final BigDecimal BET_INCREASE = new BigDecimal(1.5);
  static final int TWENTY_ONE = 21;
  static final int DECK_MINIMUM = 15;
  static final int MIN_HIT = 16;
  static String input = "yes";
  static String usersTurnPrompt = "Press 'n' for next card, 's' to stand, 'd' to double down";

  public static void play() {
    Deck currentDeck = new Deck(CasinoDriver.CHOICE1);

    while (true) {
      // reset deck if necessary
      if (currentDeck.getSize() <= DECK_MINIMUM) {
        currentDeck = new Deck(CasinoDriver.CHOICE1);
      }
      currentDeck = dealCards(currentDeck);

      System.out.println("How much do you want to bet this round?");
      bet = CasinoDriver.scan.nextBigDecimal();

      System.out.println("Players cards:  " + playersHand.getCard(0) + " and "
          + playersHand.getCard(1));
      System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");

      if (didWinNaturally(playersHand)) {
        break;
      }

      while (isStillPlaying) {
        String decision = CasinoDriver.scan.nextLine();
        
        if (decision.equals("n")) {
          currentDeck = dealNextCard(currentDeck);
        } else if (decision.equals("d")) {
          bet = bet.add(bet);
          System.out.println("Now betting $" + bet + "...");
          currentDeck = dealNextCard(currentDeck);
        } else if (decision.equals("s")) {
          // dealer has to hit if 16 or less
          if (BlackjackHandModifier.getHandValue(dealersHand) <= MIN_HIT) {
            System.out.println("Dealer's hand:  " + dealersHand.printHand());
            System.out.println("Dealer is drawing another card...");
            Card newCard = currentDeck.drawRandomCard();
            dealersHand.addCard(newCard);
          } else {
            break;
          }

          System.out.println("Dealer's hand:  " + dealersHand.printHand());

          if (didLoseBlackjack(dealersHand)) {
            System.out.println("Dealer busted");
            CasinoDriver.playersBank.addMoney(bet);
            System.out.println("$" + bet + "won");
            isStillPlaying = false;
          }
          endGame();
        }
        
        System.out.println(usersTurnPrompt);
      }



      if (CasinoDriver.playersBank.isBroke()) {
        System.out.println("No more money: "
              + CasinoDriver.playersBank.printCurrentBalance().toString());
        break;
      }

      System.out.println("New hand?");
      input = CasinoDriver.scan.nextLine();
      if (input.equals("no")) {
        break;
      } else {
        isStillPlaying = true;
      }
    }
  }


  private static Deck dealNextCard(Deck thisDeck) {
    Card newCard = thisDeck.drawRandomCard();
    playersHand.addCard(newCard);

    System.out.println("New Card:  " + newCard);

    if (didLoseBlackjack(playersHand)) {
      System.out.println("You lost with hand value: "
          + BlackjackHandModifier.getHandValue(playersHand));
      System.out.println("Dealers hand value: " + BlackjackHandModifier.getHandValue(dealersHand));

      isStillPlaying = false;
    } else {
      System.out.println("You have:  " + playersHand.printHand());
    }
    
    return thisDeck;
  }

  private static Deck dealCards(Deck thisDeck) {
    playersHand = new Hand("Player");
    dealersHand = new Hand("Dealer");

    Card playersCard1 = thisDeck.drawRandomCard();
    Card playersCard2 = thisDeck.drawRandomCard();
    playersHand.addCard(playersCard1);
    playersHand.addCard(playersCard2);

    Card dealersCard1 = thisDeck.drawRandomCard();
    Card dealersCard2 = thisDeck.drawRandomCard();
    dealersHand.addCard(dealersCard1);
    dealersHand.addCard(dealersCard2);

    System.out.println("2 cards dealt to each player, " + thisDeck.getSize()
        + " more cards in the deck");
    
    return thisDeck;
  }

  private static String getWinnerName() {
    final int pValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(playersHand);
    final int dValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(dealersHand);

    return pValue > dValue ? dealersHand.getPlayerName() : playersHand.getPlayerName();
  }

  private static boolean didWinNaturally(final Hand hand) {
    // if you have 21 to start you win 1.5 times your bet
    if (BlackjackHandModifier.getHandValue(hand) == TWENTY_ONE) {
      CasinoDriver.playersBank.addMoney(bet.multiply(BET_INCREASE));
      System.out.println("Blackjack to start! Wow!");
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static boolean didLoseBlackjack(final Hand hand) {
    if (BlackjackHandModifier.getHandValue(hand) > TWENTY_ONE) {
      System.out.println("--bust--");
      CasinoDriver.playersBank.subtractMoney(bet);
      System.out.println("Subtracting $" + bet + " from bank");
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static void endGame() {
    final int pVal = BlackjackHandModifier.getHandValue(playersHand);
    final int dVal = BlackjackHandModifier.getHandValue(dealersHand);
    if (pVal != dVal) {
      if (getWinnerName().equals("Dealer")) {
        CasinoDriver.playersBank.subtractMoney(bet);
        System.out.println("Players hand value: " + pVal);
        System.out.println("Dealers hand value: " + dVal);
        System.out.println("Subtracting $" + bet + " from bank");
      } else {
        CasinoDriver.playersBank.addMoney(bet);
        System.out.println("Adding $" + bet + " to bank");
      }
    } else {
      System.out.println("Tie");
    }
  }
}
