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
  static String input = "yes";
  static String usersTurnPrompt = "Press 'n + enter' for next card, "
      + "'s + enter' to stand, 'd + enter' to double down";

  private Blackjack() {
   
  }
  
  public static void play() {
    Deck currentDeck = new Deck(CasinoDriver.CHOICE1);

    while (true) {
      //deck set up
      currentDeck = checkNumberOfCards(currentDeck);
      currentDeck = dealCards(currentDeck);

      System.out.println("How much do you want to bet this round? " 
          + CasinoDriver.playersBank.printCurrentBalance());
      bet = CasinoDriver.scan.nextBigDecimal();
      testBetVadility();

      System.out.println("Players cards:  " + playersHand.getCard(0) 
          + " and " + playersHand.getCard(1));
      System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");

      if (didWinNaturally(playersHand)) {
        break;
      }

      while (isStillPlaying) {
        String decision = CasinoDriver.scan.nextLine();

        if ("n".equals(decision)) {
          currentDeck = dealNextCard(currentDeck);
        } else if ("d".equals(decision)) {
          playDoubleDown(bet);
          currentDeck = dealNextCard(currentDeck);
        } else if ("s".equals(decision)) {
          // dealer has to hit if 16 or less
          if (isSixteenOrLess()) {
            dealerDraws(currentDeck);
          } else {
            endGame();
            break;
          }
        } else if (!"".equals(decision)) {
          System.out.println("Invalid choice. Try again");
        }

        if (BlackjackHandEvaluator.getHandValue(playersHand) == TWENTY_ONE) {
          endGame();
          break;
        }
        
        if (isStillPlaying) {
          System.out.println(usersTurnPrompt);
        }
      }

      if (CasinoDriver.playersBank.isBroke()) {
        System.out.println("No more money: " 
            + CasinoDriver.playersBank.printCurrentBalance());
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

  private static void playDoubleDown(BigDecimal betTester) {
    BigDecimal currentBalance = CasinoDriver.playersBank.getCurrentBalance();
    if ((betTester.add(betTester)).compareTo(currentBalance) > 0) {
      System.out.println("Balance is too low to double down");
      System.out.println("Current balance: " 
          + CasinoDriver.playersBank.printCurrentBalance());
    } else {
      bet = bet.add(bet);
      System.out.println("Now betting $" + bet + "...");
    }
  }

  private static void testBetVadility() {
    BigDecimal currentBalance = CasinoDriver.playersBank.getCurrentBalance();
    while (bet.compareTo(currentBalance) > 0) {
      System.out.println("Please enter an amount less than or equal to " + currentBalance);
      bet = CasinoDriver.scan.nextBigDecimal();
    }
  }

  private static Deck checkNumberOfCards(Deck currentDeck) {
    if (currentDeck.getSize() <= DECK_MINIMUM) {
      Deck newDeck = new Deck(CasinoDriver.CHOICE1);
      return newDeck;
    } else {
      return currentDeck;
    }
  }

  private static void playerWinsByDealerBust() {
    System.out.println("Dealer busted with hand value " 
        + BlackjackHandEvaluator.getHandValue(dealersHand));
    CasinoDriver.playersBank.addMoney(bet);
    System.out.println("Player won!!  Adding $" + bet + " to your bank!!");
    isStillPlaying = false;
  }

  private static boolean isSixteenOrLess() {
    return BlackjackHandEvaluator.getHandValue(dealersHand) <= MIN_HIT;
  }

  private static void dealerDraws(Deck thisDeck) {
    System.out.println("Dealer's hand value is less than 17...so dealer is drawing another card");
    Card newCard = thisDeck.drawRandomCard();
    dealersHand.addCard(newCard);
    System.out.println("Dealer's hand:  " + dealersHand.printHand());

    if (didLoseBlackjack(dealersHand)) {
      playerWinsByDealerBust();
    }
  }

  private static Deck dealNextCard(Deck thisDeck) {
    Card newCard = thisDeck.drawRandomCard();
    playersHand.addCard(newCard);

    System.out.println("New Card:  " + newCard);

    if (didLoseBlackjack(playersHand)) {
      System.out.println("Player lost with hand value: " 
          + BlackjackHandEvaluator.getHandValue(playersHand));
      System.out.println("Dealers hand value: " + BlackjackHandEvaluator.getHandValue(dealersHand));

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

    System.out.println("2 cards dealt to each player, " 
        + thisDeck.getSize() + " more cards in the deck");

    return thisDeck;
  }

  private static String getWinnerName() {
    final int pValue = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(playersHand);
    final int dValue = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(dealersHand);

    return pValue > dValue ? dealersHand.getPlayerName() : playersHand.getPlayerName();
  }

  private static boolean didWinNaturally(Hand hand) {
    // if you have 21 to start you win 1.5 times your bet
    if (BlackjackHandEvaluator.getHandValue(hand) == TWENTY_ONE) {
      CasinoDriver.playersBank.addMoney(bet.multiply(BET_INCREASE));
      System.out.println("Blackjack to start! Wow!");
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static boolean didLoseBlackjack(Hand hand) {
    if (BlackjackHandEvaluator.getHandValue(hand) > TWENTY_ONE) {
      if ("Player".equals(hand.getPlayerName())) {
        System.out.println("--bust--");
        CasinoDriver.playersBank.subtractMoney(bet);
        System.out.println("Subtracting $" + bet + " from bank");
      }
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private static void endGame() {
    final int pVal = BlackjackHandEvaluator.getHandValue(playersHand);
    final int dVal = BlackjackHandEvaluator.getHandValue(dealersHand);
    
    System.out.println("Player's hand:  " + playersHand.printHand());
    System.out.println("Players hand value: " + pVal);
    System.out.println("Dealer's hand:  " + dealersHand.printHand());
    System.out.println("Dealers hand value: " + dVal);
    
    if (pVal != dVal) {
      if ("Dealer".equals(getWinnerName())) {
        CasinoDriver.playersBank.subtractMoney(bet);
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
