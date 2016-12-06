package edu.calpoly.cpe305;

public class PokerHandEvaluator extends Hand {
  public PokerHandEvaluator(String inputName) {
    super(inputName);
  }

  // at least one pair
  public static boolean hasPair(Hand currentHand) {
    boolean isPair = false;

    // count same cards
    int count = 0;
    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.getCard(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.getCard(j))) {
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

  public static boolean hasTwoPairs(Hand currentHand) {
    boolean twoPairs = false;

    // count same cards
    int count;
    int number = 0;
    for (int i = 0; i < 4; i++) {
      count = 0;

      Card compare = currentHand.getCard(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.getCard(j))) {
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
  public static boolean hasFacePair(Hand currentHand) {
    boolean isPair = false;
    int count = 0;
    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.getCard(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equalsPair(currentHand.getCard(j))) {
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
  public static boolean hasTriple(Hand currentHand) {
    boolean isTriple = false;
    int count;

    // escape loop if 3 of a kind
    int finished = 10;

    for (int i = 0; i < 4; i++) {
      count = 0;
      Card compare = currentHand.getCard(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.getCard(j))) { // this is in the card class 
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

  public static boolean hasStraight(Hand currentHand) { // determines if you have a strait
    boolean hasStrait = false;
    currentHand.organize();
    int position = currentHand.getCard(0).getPosition();
    int count = 0;
    for (int i = 1; i < 5; i++) {
      // if one more than card before it
      if (currentHand.getCard(i).getPosition() == position + i) {
        count++;
      }
    }

    if (count == 4) {
      hasStrait = true;
    }

    return hasStrait;
  }

  // 4 of same suit
  public static boolean hasFlush(Hand currentHand) {
    Card compare = currentHand.getCard(0);
    int count = 0;
    for (int i = 1; i < 5; i++) {
      if (currentHand.getCard(i).getSuit().equals(compare.getSuit())) {
        count++;
      }
    }
    return (count == 4);
  }

  // 2 pairs with one triple
  public static boolean hasFullHouse(Hand currentHand) {
    return (hasTriple(currentHand) && hasTwoPairs(currentHand));
  }

  // 4 of a kind
  public static boolean hasQuad(Hand currentHand) {
    boolean isQuad = false;
    int count = 0;

    // escape loop
    int finished = 10;

    for (int i = 0; i < 4; i++) {
      Card compare = currentHand.getCard(i);
      for (int j = (i + 1); j < 5; j++) {
        if (compare.equals(currentHand.getCard(j))) {
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
  public static boolean hasStraightFlush(Hand currentHand) {
    return hasStraight(currentHand) && hasFlush(currentHand);
  }

  // straight flush face cards
  public static boolean hasRoyalFlush(Hand currentHand) {
    boolean royal = false;

    // increasing order
    currentHand.organize();

    // first card 10
    int position = 10;

    int count = 0;
    // check straight
    for (int i = 0; i < 5; i++) {
      if (currentHand.getCard(i).getPosition() == position + i) {
        count = count + 1;
      }
    }

    if (count == 5) {
      royal = true;
    }

    return hasFlush(currentHand) && royal;
  }
}
