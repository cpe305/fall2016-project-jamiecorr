package edu.calpoly.cpe305.test;

import static org.junit.Assert.assertEquals;

import edu.calpoly.cpe305.Deck;

import org.junit.Test;

public class TestDeckBJ {
  Deck deck = new Deck(1);

  @Test
  public void testSize() {
    assertEquals(52, deck.getSize());
  }

  @Test
  public void testCreateDeck() {
    assertEquals("2 of clubs", deck.getCardInPosition(0).toString());
    assertEquals("3 of diamonds", deck.getCardInPosition(14).toString());
    assertEquals("King of hearts", deck.getCardInPosition(37).toString());
    assertEquals("Ace of spades", deck.getCardInPosition(51).toString());
  }

  @Test
  public void testDrawRandomCard() {
    deck.drawRandomCard();
    assertEquals(51, deck.getSize());
  }
}