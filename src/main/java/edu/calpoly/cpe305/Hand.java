package edu.calpoly.cpe305;

import java.util.ArrayList;

public class Hand {
  private String playerName;
  private ArrayList<Card> currentHand;
  static final double ACE_VALUE = 14;

  public Hand(String playerName) {
    currentHand = new ArrayList<>();
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Card getCard(int idx) {
    return (Card) currentHand.get(idx);
  }

  public void addCard(Card card) {
    currentHand.add(card);
  }

  public String printHand() {
    StringBuilder currentHandString = new StringBuilder();
    for (int i = 0; i < currentHand.size(); i++) {
      currentHandString.append(currentHand.get(i) + "  ");
    }
    return currentHandString.toString();
  }

  public int size() {
    return currentHand.size();
  }

  // organize cards by increasing value
  public void organize() {
    ArrayList<Card> newHand = new ArrayList<>();

    // order can be between 2 and 14
    for (int i = 2; i <= 14; i++) {
      for (int j = 0; j < 5; j++) {
        if (currentHand.get(j).getPosition() == i) {
          newHand.add(currentHand.get(j));
        }
      }
    }
    currentHand = newHand;
  }

  // redeal currentHand
  public void reDeal(String test1, Deck test2) {
    int count = 0;

    boolean one = false;
    boolean two = false;
    boolean three = false;
    boolean four = false;
    boolean five = false;

    ArrayList<Card> setHand = new ArrayList<Card>();

    Card one1 = currentHand.get(0);
    Card two2 = currentHand.get(1);
    Card three3 = currentHand.get(2);
    Card four4 = currentHand.get(3);
    Card five5 = currentHand.get(4);

    // which card player chooses to change
    for (int i = 0; i < test1.length(); i++) {
      if (test1.charAt(i) == '1' || test1.charAt(i) == '2' || test1.charAt(i) == '3' || test1.charAt(i) == '4'
          || test1.charAt(i) == '5') {
        // true if chosen
        if (test1.charAt(i) == '1')
          one = true;
        if (test1.charAt(i) == '2')
          two = true;
        if (test1.charAt(i) == '3')
          three = true;
        if (test1.charAt(i) == '4')
          four = true;
        if (test1.charAt(i) == '5')
          five = true;

        // how many card switched out so it can add new random cards
        count++;
      }
    }

    // number of changed cards
    for (int j = 0; j < count; j++) {
      setHand.add(test2.drawRandomCard());
    }

    // if not chose keep in currentHand
    if (one != true)
      setHand.add(one1);

    if (two != true)
      setHand.add(two2);

    if (three != true)
      setHand.add(three3);

    if (four != true)
      setHand.add(four4);

    if (five != true)
      setHand.add(five5);

    currentHand = setHand;
  }
}