package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Blackjack {
  static Hand playersHand;
  static Hand dealersHand;
  static boolean isStillPlaying = true;
  static BigDecimal bet;
  static final BigDecimal BET_INCREASE = BigDecimal.valueOf(1.5);
  static final int TWENTY_ONE = 21;
  static final int DECK_MINIMUM = 15;
  static final int MIN_HIT = 16;
  static String decision;
  static String input = "yes";
  static String usersTurnPrompt = "Press 'n' for next card, 's' to stand, 'd' to double down";

  public static void play() {
    Deck currentDeck = new Deck("John");

    while (true) {
      // reset deck if necessary
      if (currentDeck.getSize() <= DECK_MINIMUM) {
        currentDeck = new Deck("Jamie");
      }
      dealCards(currentDeck);

      System.out.println("How much do you want to bet this round?");
      bet = CasinoDriver.scan.nextBigDecimal();

      System.out.println("Players cards:  " + playersHand.getCard(0) + " and "
          + playersHand.getCard(1));
      System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");

      if (didWinNaturally(playersHand)) {
        break;
      }

      while (isStillPlaying) {
        System.out.println(usersTurnPrompt);
        decision = CasinoDriver.scan.nextLine();

        if ("n".equals(decision)) {
          dealNextCard(currentDeck);
        } else if ("d".equals(decision)) {
          bet = bet.add(bet);
          System.out.println("Now betting $" + bet + "...");
          dealNextCard(currentDeck);
        } else if ("s".equals(decision)) {
          // dealer has to hit if 16 or less
          if (BlackjackHandEvaluator.getHandValue(dealersHand) <= MIN_HIT) {
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
      }



      if (CasinoDriver.playersBank.isBroke()) {
        System.out.println("No more money");
        break;
      }

      System.out.println("New hand?");
      input = CasinoDriver.scan.nextLine();
      if ("no".equals(input)) {
        break;
      } else {
        isStillPlaying = true;
      }
    }
  }


  public Blackjack() {
    super();
    // TODO Auto-generated constructor stub
  }


  private static void dealNextCard(final Deck currentDeck) {
    final Card newCard = currentDeck.drawRandomCard();
    playersHand.addCard(newCard);

    System.out.println("New Card:  " + newCard);

    if (didLoseBlackjack(playersHand)) {
      System.out.println("You lost with hand value: "
          + BlackjackHandEvaluator.getHandValue(playersHand));
      System.out.println("Dealers hand value: " + BlackjackHandEvaluator.getHandValue(dealersHand));

      isStillPlaying = false;
    } else {
      System.out.println("You have:  " + playersHand.printHand());
    }
  }

  private static void dealCards(final Deck thisDeck) {
    playersHand = new Hand("Player");
    dealersHand = new Hand("Dealer");

    final Card playersCard1 = thisDeck.drawRandomCard();
    final Card playersCard2 = thisDeck.drawRandomCard();
    playersHand.addCard(playersCard1);
    playersHand.addCard(playersCard2);

    final Card dealersCard1 = thisDeck.drawRandomCard();
    final Card dealersCard2 = thisDeck.drawRandomCard();
    dealersHand.addCard(dealersCard1);
    dealersHand.addCard(dealersCard2);

    System.out.println("2 cards dealt to each player, " + thisDeck.getSize()
        + " more cards in the deck");
  }

  private static String getWinnerName() {
    final int pValue = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(playersHand);
    final int dValue = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(dealersHand);

    return pValue > dValue ? dealersHand.getPlayerName() : playersHand.getPlayerName();
  }

  private static boolean didWinNaturally(final Hand hand) {
    // if you have 21 to start you win 1.5 times your bet
    if (BlackjackHandEvaluator.getHandValue(hand) == TWENTY_ONE) {
      CasinoDriver.playersBank.addMoney(bet.multiply(BET_INCREASE));
      System.out.println("Blackjack to start! Wow!");
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static boolean didLoseBlackjack(final Hand hand) {
    if (BlackjackHandEvaluator.getHandValue(hand) > TWENTY_ONE) {
      System.out.println("--bust--");
      CasinoDriver.playersBank.subtractMoney(bet);
      System.out.println("Subtracting $" + bet + " from bank");
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static void endGame() {
    final int pVal = BlackjackHandEvaluator.getHandValue(playersHand);
    final int dVal = BlackjackHandEvaluator.getHandValue(dealersHand);
    if (pVal != dVal) {
      if ("Dealer".equals(getWinnerName())) {
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
