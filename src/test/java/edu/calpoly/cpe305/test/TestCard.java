package edu.calpoly.cpe305.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.Card;

public class TestCard {
  Card card = new Card("2", "clubs", 2);

  @Test
  public void testGetSuit() {
    assertEquals("clubs", card.getSuit());
  }

  @Test
  public void testGetValue() {
    assertEquals(2, card.getValue());
  }
  
  @Test
  public void testGetName() {
    assertEquals("2", card.getValue());
  }

  @Test
  public void testToString() {
    assertEquals("2 of clubs", card.toString());
  }
}