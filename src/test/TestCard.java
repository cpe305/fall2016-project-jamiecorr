package edu.calpoly.cpe305.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.Card;

public class TestCard {
  Card card = new Card("Two", "clubs", 5);

  @Test
  public void testGetSuit() {
    assertEquals("clubs", card.getSuit());
  }

  @Test
  public void testGetValue() {
    assertEquals(5, card.getValue());
  }

  @Test
  public void testToString() {
    assertEquals("Two of clubs", card.toString());
  }
}