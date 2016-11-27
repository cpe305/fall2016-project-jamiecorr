package edu.calpoly.cpe305;

import java.util.ArrayList;

public class PokerHandEvaluator extends Hand {
  private ArrayList<Card> currentHand;

  public PokerHandEvaluator(String inputName) {
    super(inputName);
  }

  // at least one pair
  public boolean hasPair() {
    boolean isPair = false;

    // count same cards
    int count = 0;
    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.get(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.get(j))) {
          isPair = true;
          count = 1;
          break;
        }
      }
      if (count == 1) {
        break;
      }
    }
    return isPair;
  }

  public boolean hasTwoPairs() {
    boolean twoPairs = false;

    // count same cards
    int count = 0;
    int number = 0;
    for (int i = 0; i < 4; i++) {
      count = 0;

      Card compare = currentHand.get(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.get(j))) {
          // one pair
          if (count == 1) {
            number = number + 1;
          }

          // two pairs
          if (count == 2) {
            number = number - 1;
          }

          count = count + 1;
        }
      }
    }

    if (number == 2) {
      twoPairs = true;
    }

    return twoPairs;
  }

  // face card pair
  public boolean hasFacePair() {
    boolean isPair = false;
    int count = 0;
    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.get(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equalsPair(currentHand.get(j))) {
          isPair = true;
          count = 1;
          break;
        }
      }

      if (count == 1) {
        break;
      }
    }
    return isPair;
  }

  // 3 of a kind
  public boolean hasTriple() {
    boolean isTriple = false;
    int count = 0;

    // escape loop if 3 of a kind
    int finished = 10;

    for (int i = 0; i < 4; i++) {
      count = 0;
      Card compare = currentHand.get(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.get(j))) { // this is in the card class 
          count = count + 1;
          if (count == 2) {
            isTriple = true;
          }

          if (count == 3) {
            isTriple = false;
            finished = 100;
            break;
          }
        }
      }

      if (finished == 100) {
        break;
      }
    }
    
    return isTriple;
  }

  public boolean hasStraight() { // determines if you have a strait
    boolean hasStrait = false;
    organize();
    int position = currentHand.get(0).getPosition();
    int count = 0;
    for (int i = 1; i < 5; i++) {
      // if one more than card before it
      if (currentHand.get(i).getPosition() == position + i) {
        count++;
      }
    }

    if (count == 4) {
      hasStrait = true;
    }

    return hasStrait;
  }

  // 4 of same suit
  public boolean hasFlush() {
    Card compare = currentHand.get(0);
    int count = 0;
    for (int i = 1; i < 5; i++) {
      if (currentHand.get(i).getSuit().equals(compare.getSuit())) {
        count++;
      }
    }
    return (count == 4);
  }

  // 2 pairs with one triple
  public boolean hasFullHouse() {
    return (hasTriple() && hasTwoPairs());
  }

  // 4 of a kind
  public boolean hasQuad() {
    boolean isQuad = false;
    int count = 0;

    // escape loop
    int finished = 10;

    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.get(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.get(j))) {
          count = count + 1;
          if (count == 4) {
            finished = 100;
            break;
          }
        }
      }

      if (finished == 100) {
        break;
      }
    }

    if (count == 4) {
      isQuad = true;
    }

    return isQuad;
  }

  // same suit flush
  public boolean hasStraightFlush() {
    return hasStraight() && hasFlush();
  }

  // straight flush face cards
  public boolean hasRoyalFlush() {
    boolean royal = false;

    // increasing order
    organize();

    // first card 10
    int position = 10;

    int count = 0;
    // check straight
    for (int i = 0; i < 5; i++) {
      if (currentHand.get(i).getPosition() == position + i) {
        count = count + 1;
      }
    }

    if (count == 5) {
      royal = true;
    }

    return hasFlush() && royal;
  }
}
