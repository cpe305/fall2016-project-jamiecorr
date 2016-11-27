package edu.calpoly.cpe305;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deck {
  private static final Logger LOGGER = Logger.getLogger(Logger.class.getName());

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
  private ArrayList<Card> newDeck; // same as newDeck in Poker
  private static final int MAX_CARD_POSITION = 14;
  private static final int NONFACECARD_STARTING_VALUE = 2;
  private static final int FACECARD_VALUE = 10;
  private String name;

  public Deck(String inputName) {
    newDeck = new ArrayList<>();
    createDeck();
    name = inputName;
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
      System.out.print(newDeck.get(i).toString() + "\n");
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
    for (int value = NONFACECARD_STARTING_VALUE; value <= FACECARD_VALUE; value++) {
      newDeck.add(new Card(String.valueOf(value), suitChoice, value, value));
    }
  }

  private void set_FaceCard_Values(String suitChoice) {
    newDeck.add(new Card("Jack", suitChoice, FACECARD_VALUE, 11));
    newDeck.add(new Card("Queen", suitChoice, FACECARD_VALUE, 12));
    newDeck.add(new Card("King", suitChoice, FACECARD_VALUE, 13));
    newDeck.add(new Card("Ace", suitChoice, FACECARD_VALUE + 1, 14));
  }
}