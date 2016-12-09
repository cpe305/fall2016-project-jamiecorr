package edu.calpoly.cpe305;

public class Card {
  private String name;
  private String suit;
  private int value;
  private int position;

  public Card(String name, String suit, int value, int position) {
    this.name = name;
    this.suit = suit;
    this.value = value;
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public String getSuit() {
    return suit;
  }

  public int getValue() {
    return value;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public String toString() {
    return name + " of " + suit;
  }
}