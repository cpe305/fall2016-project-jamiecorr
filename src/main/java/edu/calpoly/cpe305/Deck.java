package edu.calpoly.cpe305;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
  public enum Suits {
    CLUBS("clubs"), DIAMONDS("diamonds"), HEARTS("hearts"), SPADES("spades");

    private final String text;

    private Suits(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }
  }

  Suits suits;
  private ArrayList<Card> newDeck;
  private static final int MAX_CARD_VALUE = 11;
  private static final int NONFACECARD_STARTING_VALUE = 2;
  private static final int FACECARD_STARTING_VALUE = 10;
  private static final int MIN_CARD_VALUE = 1;

  public Deck(int gameChoice) {
    newDeck = new ArrayList<>();
    if (gameChoice == 1) {
      createDeck();
    }
  }

  public int getSize() {
    return newDeck.size();
  }

  public Card getCardInPosition(int ndx) {
    return newDeck.get(ndx);
  }

  public Card drawRandomCard() {
    Random generator = new Random();
    int cardValue = generator.nextInt(newDeck.size());
    Card test = newDeck.get(cardValue);

    newDeck.remove(cardValue);
    return test;
  }

  public void printCards() {
    for (int i = 0; i < newDeck.size(); i++) {
      System.out.println(newDeck.get(i).toString());
      System.out.println();
    }
  }

  public void createDeck() {
    setValues(Suits.CLUBS.toString());
    setValues(Suits.DIAMONDS.toString());
    setValues(Suits.HEARTS.toString());
    setValues(Suits.SPADES.toString());
  }
  
  private void setValues(String suit) {
    set_NonFaceCard_Values(suit);
    set_FaceCard_Values(suit);
  }

  private void set_NonFaceCard_Values(String suitChoice) {
    for (int value = NONFACECARD_STARTING_VALUE; value < FACECARD_STARTING_VALUE; value++) {
      newDeck.add(new Card(String.valueOf(value), suitChoice, value));
    }
  }

  private void set_FaceCard_Values(String suitChoice) {
    for (int value = FACECARD_STARTING_VALUE; value <= MAX_CARD_VALUE; value++) {
      if (value == FACECARD_STARTING_VALUE) {
        newDeck.add(new Card(String.valueOf(10), suitChoice, value));
        newDeck.add(new Card("Jack", suitChoice, value));
        newDeck.add(new Card("Queen", suitChoice, value));
        newDeck.add(new Card("King", suitChoice, value));
      } else {
        newDeck.add(new Card("Ace", suitChoice, value));
      }
    }
  }
}