package edu.calpoly.cpe305;

import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BlackjackHandEvaluator extends Hand {
  private static final int TWENTY_ONE = 21;
  private static final int ACE_RESET = 10;
//  private final IntegerProperty valueOfHand = new SimpleIntegerProperty();

  public BlackjackHandEvaluator(String inputName) {
    super(inputName);
  }

  public static int getHandValue(Hand hand) {
    int total = getTotalValue(hand);

    if (countAces(hand) > 0 && hasBustWithAces(hand)) {
      // if ace makes you bust then count it as 1
      total -= (ACE_RESET * countAces(hand));
    }
    return total;
  }

  // counts ace as 11 first to see if its over 21
  public static int getTotalValue(Hand hand) {
    int total = 0;
    for (int i = 0; i < hand.size(); i++) {
      total += hand.getCard(i).getValue();
    }
    return total;
  }

  // return number of aces in hand
  public static int countAces(Hand hand) {
    int count = 0;
    for (int i = 0; i < hand.size(); i++) {
      if ("Ace".equals(hand.getCard(i).getName())) {
        count++;
      }
    }
    return count;
  }

  private static boolean hasBustWithAces(Hand hand) {
    return getTotalValue(hand) > TWENTY_ONE;
  }

  // before considering aces
  public boolean testHandValue(Hand hand) {
    // if over 21 sends to getValue to look at aces
    return getTotalValue(hand) > TWENTY_ONE;
  }
}
