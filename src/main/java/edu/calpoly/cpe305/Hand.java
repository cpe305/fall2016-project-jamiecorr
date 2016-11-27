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
}