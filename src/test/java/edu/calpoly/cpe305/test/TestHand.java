package edu.calpoly.cpe305.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.Card;
import edu.calpoly.cpe305.Hand;

public class TestHand {
  Hand myHand = new Hand("Jamie");

  @Test
  public void testGetPlayerName() {
    assertEquals("Jamie", myHand.getPlayerName());
  }
  
  @Test
  public void testSize() {
    assertEquals(0, myHand.size());
  }

  @Test
  public void testGetCardInPosition() {
    Card card = new Card("Two", "clubs", 2, 2);
    myHand.addCard(card);

    assertEquals(card, myHand.getCard(0));
  }
  
  @Test
  public void testPrintHand() {
    Card card1 = new Card("2", "clubs", 2, 2);
    Card card2 = new Card("10", "hearts", 10, 10);
    
    myHand.addCard(card1);
    myHand.addCard(card2);
    
    String output = myHand.printHand();
    assertTrue("2 of clubs  10 of hearts  ".equals(output));
  }
}
