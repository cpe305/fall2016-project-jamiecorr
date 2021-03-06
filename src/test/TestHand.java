package edu.calpoly.cpe305.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.Card;
import edu.calpoly.cpe305.Hand;

public class TestHand {
  Hand hand = new Hand("Jamie");

  @Test
  public void testGetPlayerName() {
    assertEquals("Jamie", hand.getPlayerName());
  }

  @Test
  public void testGetCardInPosition() {
    Card card = new Card("Two", "clubs", 5);
    hand.addCard(card);

    assertEquals(card, hand.getCard(0));
  }
}
