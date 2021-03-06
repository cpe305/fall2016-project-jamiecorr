package edu.calpoly.cpe305.test;

import static org.junit.Assert.assertEquals;

import edu.calpoly.cpe305.BlackjackHandEvaluator;
import edu.calpoly.cpe305.Card;
import edu.calpoly.cpe305.Hand;
import org.junit.Test;

public class TestEvaluator {
  Hand myHand = new Hand("Jamie");
  Card card1 = new Card("2", "clubs", 2, 2);
  Card card2 = new Card("10", "hearts", 10, 10);
  Card card3 = new Card("Ace", "clubs", 11, 14);
  Card card4 = new Card("Jack", "diamonds", 10, 11);
  Card card5 = new Card("Jack", "diamonds", 10, 11);
  Card card6 = new Card("9", "spades", 9, 9);
  Card card7 = new Card("Ace", "spades", 11, 14);


  @Test
  public void testGetHandValue() {
    myHand.addCard(card1);
    myHand.addCard(card2);
    myHand.addCard(card3);

    int lowValue = BlackjackHandEvaluator.getHandValue(myHand);
    int lowTotValue = BlackjackHandEvaluator.getTotalValue(myHand);
    assertEquals(13, lowValue);
    assertEquals(23, lowTotValue);

    myHand.addCard(card4);
    myHand.addCard(card5);
    myHand.addCard(card6);
    myHand.addCard(card7);

    int highValue = BlackjackHandEvaluator.getHandValue(myHand);
    int highTotValue = BlackjackHandEvaluator.getTotalValue(myHand);

    assertEquals(43, highValue);
    assertEquals(63, highTotValue);

  }

  @Test
  public void testCountAces() {
    myHand.addCard(card1);
    myHand.addCard(card2);
    myHand.addCard(card3);

    int value = BlackjackHandEvaluator.countAces(myHand);
    assertEquals(1, value);
  }

}
