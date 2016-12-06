package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Blackjack {
  Hand playersHand;
  Hand dealersHand;
  boolean isStillPlaying = true;
  boolean isPlaying = true;
  BigDecimal bet;
  static final BigDecimal BET_INCREASE = BigDecimal.valueOf(1.5);
  static final int TWENTY_ONE = 21;
  static final int DECK_MINIMUM = 15;
  static final int MIN_HIT = 16;
  String decision = "x";
  String input;
  String usersTurnPrompt = "Press 'n' for next card, 's' to stand, 'd' to double down";
  Deck thisDeck;

  public Blackjack() {
    playersHand = new Hand("Player");
    dealersHand = new Hand("Dealer");
    thisDeck = new Deck();
    play(playersHand, dealersHand, bet, thisDeck);
  }

  private void newHand(Deck oldDeck) {
    playersHand = new Hand("Player");
    dealersHand = new Hand("Dealer");
    play(playersHand, dealersHand, bet, oldDeck);
  }

  public void play(Hand playersHand, Hand dealersHand, BigDecimal bet, Deck currentDeck) {
    while (isPlaying) {
      // reset deck if necessary
      if (currentDeck.getSize() <= DECK_MINIMUM) {
        currentDeck = new Deck();
      }
      playersHand = dealCards(playersHand, currentDeck);
      dealersHand = dealCards(dealersHand, currentDeck);

      System.out.println("2 cards dealt to each player, " + currentDeck.getSize() + " more cards in the deck");

      System.out.println("How much do you want to bet this round?");
      bet = CasinoDriver.scan.nextBigDecimal();

      System.out.println("Players cards:  " + playersHand.getCard(0) + " and " + playersHand.getCard(1));
      System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");

      if (didBust(playersHand)) {
        System.out.println("You lost with hand value: " + BlackjackHandEvaluator.getHandValue(playersHand));
        System.out.println("Dealers hand value: " + BlackjackHandEvaluator.getHandValue(dealersHand));

        isStillPlaying = false;
      }

      if (didWinNaturally(playersHand, bet)) {
        isPlaying = restart(currentDeck);
      }

      isStillPlaying = true;
      int count = 0;
      while (isStillPlaying) {
        if ("n".equals(decision)) {
          playersHand = dealNextCard(playersHand, currentDeck);
          System.out.println("Player's hand:  " + playersHand.printHand());
        } else if ("d".equals(decision)) {
          bet = bet.add(bet);
          System.out.println("Now betting $" + bet + "...");
          playersHand = dealNextCard(playersHand, currentDeck);
        } else if ("s".equals(decision)) {
          // dealer has to hit if 16 or less
          if (BlackjackHandEvaluator.getHandValue(dealersHand) <= MIN_HIT) {
            Card newCard = currentDeck.drawRandomCard();
            dealersHand.addCard(newCard);
          } else {
            endGame(playersHand, dealersHand, bet);
            break;
          }

          System.out.println("Dealer's hand:  " + dealersHand.printHand());

          if (didBust(dealersHand)) {
            System.out.println("Dealer busted");
            // CasinoDriver.playersBank.addMoney(bet);
            // System.out.println("$" + bet + "won");
            isStillPlaying = false;
          }

          if (BlackjackHandEvaluator.getHandValue(dealersHand) > MIN_HIT) {
            endGame(playersHand, dealersHand, bet);
            break;
          }
        }

        if (BlackjackHandEvaluator.getHandValue(playersHand) > TWENTY_ONE) {
          endGame(playersHand, dealersHand, bet);
          break;
        } else {
          System.out.println(usersTurnPrompt);
          decision = CasinoDriver.scan.nextLine();
        }
      }

      if (CasinoDriver.playersBank.isBroke()) {
        System.out.println("No more money");
        isPlaying = false;
        break;
      } else {
        isPlaying = restart(currentDeck);
        break;
      }
    }
  }

  private boolean restart(Deck myDeck) {
    System.out.println("New hand?");
    input = CasinoDriver.scan.nextLine();
    if ("yes".equals(input)) {
      newHand(myDeck);
      isStillPlaying = true;
      return true;
    } else {
      return false;
    }
  }

  private Hand dealNextCard(Hand currentHand, Deck currentDeck) {
    Card newCard = currentDeck.drawRandomCard();
    currentHand.addCard(newCard);

    System.out.println("New Card:  " + newCard);
    return currentHand;
  }

  private Hand dealCards(Hand currentHand, Deck thisDeck) {
    currentHand.addCard(thisDeck.drawRandomCard());
    currentHand.addCard(thisDeck.drawRandomCard());
    return currentHand;
  }

  private String getWinnerName(Hand playersHand, Hand dealersHand) {
    int valueP = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(playersHand);
    int valueD = TWENTY_ONE - BlackjackHandEvaluator.getHandValue(dealersHand);

    if (didBust(playersHand)) {
      return dealersHand.getPlayerName();
    } else if (didBust(dealersHand)) {
      return playersHand.getPlayerName();
    } else {
      return valueP < valueD ? playersHand.getPlayerName() : dealersHand.getPlayerName();
    }
  }

  private boolean didWinNaturally(final Hand hand, BigDecimal bet) {
    // if you have 21 to start you win 1.5 times your bet
    if (BlackjackHandEvaluator.getHandValue(hand) == TWENTY_ONE) {
      CasinoDriver.playersBank.addMoney(bet.multiply(BET_INCREASE));
      System.out.println("Blackjack to start! Wow!");
      System.out.println(CasinoDriver.playersBank.printCurrentBalance());
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private boolean didBust(final Hand hand) {
    if (BlackjackHandEvaluator.getHandValue(hand) > TWENTY_ONE) {
      isStillPlaying = false;
      return true;
    }
    return false;
  }

  private void endGame(Hand playersHand, Hand dealersHand, BigDecimal bet) {
    int pVal = BlackjackHandEvaluator.getHandValue(playersHand);
    int dVal = BlackjackHandEvaluator.getHandValue(dealersHand);
    if (pVal != dVal) {
      if ("Dealer".equals(getWinnerName(playersHand, dealersHand))) {
        CasinoDriver.playersBank.subtractMoney(bet);
        System.out.println("Players hand value: " + pVal);
        System.out.println("Dealers hand value: " + dVal);
        System.out.println("Subtracting $" + bet + " from bank");
        System.out.println(CasinoDriver.playersBank.printCurrentBalance());
      } else {
        CasinoDriver.playersBank.addMoney(bet);
        System.out.println("Adding $" + bet + " to bank");
        System.out.println(CasinoDriver.playersBank.printCurrentBalance());
      }
    } else {
      System.out.println("Tie");
      isPlaying = restart(thisDeck);
    }
  }
}
