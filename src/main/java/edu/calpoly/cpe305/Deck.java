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

  public Deck(int gameChoice) {
    newDeck = new ArrayList<Card>();
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

  public void print(String statement) {

  }

  public void createDeck() {
    setValues(Suits.CLUBS.toString());
    setValues(Suits.DIAMONDS.toString());
    setValues(Suits.HEARTS.toString());
    setValues(Suits.SPADES.toString());
  }

  public void setValues(String suitChoice) {
    for (int value = 2; value <= MAX_CARD_VALUE; value++) {
      switch (value) {
        case 2:
          newDeck.add(new Card("Two", suitChoice, value));
          break;
        case 3:
          newDeck.add(new Card("Three", suitChoice, value));
          break;
        case 4:
          newDeck.add(new Card("Four", suitChoice, value));
          break;
        case 5:
          newDeck.add(new Card("Five", suitChoice, value));
          break;
        case 6:
          newDeck.add(new Card("Six", suitChoice, value));
          break;
        case 7:
          newDeck.add(new Card("Seven", suitChoice, value));
          break;
        case 8:
          newDeck.add(new Card("Eight", suitChoice, value));
          break;
        case 9:
          newDeck.add(new Card("Nine", suitChoice, value));
          break;
        case 10:
          newDeck.add(new Card("Ten", suitChoice, value));
          newDeck.add(new Card("Jack", suitChoice, value));
          newDeck.add(new Card("Queen", suitChoice, value));
          newDeck.add(new Card("King", suitChoice, value));
          break;
        case 11:
          newDeck.add(new Card("Ace", suitChoice, value));
          break;
        default:
          System.out.println("invalid value");
          break;
      }
    }
  }
}