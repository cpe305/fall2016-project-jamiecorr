package edu.calpoly.cpe305;

import java.util.ArrayList;

public class Hand {
  private String playerName;
  private ArrayList<Card> hand;
  static final double ACE_VALUE = 14;

  public Hand(String playerName) {
    hand = new ArrayList<Card>();
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Card getCard(int idx) {
    return hand.get(idx);
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public String printHand() {
    StringBuilder bld = new StringBuilder();
    for (int i = 0; i < hand.size(); i++) {
      bld.append(hand.get(i) + "  ");
    }
    return bld.toString();
  }

  public void print(String statement) {

  }

  public int size() {
    return hand.size();
  }
}