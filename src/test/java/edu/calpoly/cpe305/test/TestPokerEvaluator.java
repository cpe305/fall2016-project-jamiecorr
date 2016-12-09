package edu.calpoly.cpe305.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.calpoly.cpe305.Card;
import edu.calpoly.cpe305.Deck;
import edu.calpoly.cpe305.Hand;
import edu.calpoly.cpe305.PokerHandEvaluator;

public class TestPokerEvaluator {
  Hand myHand = new Hand("Jamie");


  @Test
  public void testHasRoyal() {
    Card c14s = new Card("Ace", "clubs", 11, 14);
    Card c13s = new Card("King", "clubs", 10, 13);
    Card c12s = new Card("Queen", "clubs", 10, 12);
    Card c11s = new Card("Jack", "clubs", 10, 11);
    Card c10s = new Card("10", "clubs", 10, 10);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasRoyalFlush(myHand));
  }

  @Test
  public void testHasQuad() {
    Card c14s = new Card("Ace", "clubs", 11, 14);
    Card c13s = new Card("Ace", "hearts", 11, 14);
    Card c12s = new Card("Ace", "diamonds", 11, 14);
    Card c11s = new Card("Ace", "spades", 11, 14);
    Card c10s = new Card("10", "clubs", 10, 10);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasQuad(myHand));
  }
  
  @Test
  public void testHasFacePair() {
    Card c14s = new Card("King", "clubs", 10, 13);
    Card c13s = new Card("10", "hearts", 10, 10);
    Card c12s = new Card("6", "diamonds", 6, 6);
    Card c11s = new Card("Ace", "spades", 11, 14);
    Card c10s = new Card("King", "clubs", 10, 13);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasFacePair(myHand));
  }
  
  @Test
  public void testHasPair() {
    Card c14s = new Card("Ace", "clubs", 11, 14);
    Card c13s = new Card("2", "hearts", 2, 2);
    Card c12s = new Card("Jack", "diamonds", 10, 11);
    Card c11s = new Card("2", "spades", 2, 2);
    Card c10s = new Card("10", "clubs", 10, 10);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasPair(myHand));
  }
  
  @Test
  public void testHasStraightFlush() {
    Card c14s = new Card("3", "clubs", 3, 3);
    Card c13s = new Card("4", "clubs", 4, 4);
    Card c12s = new Card("5", "clubs", 5, 5);
    Card c11s = new Card("6", "clubs", 6, 6);
    Card c10s = new Card("7", "clubs", 7, 7);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasStraightFlush(myHand));
  }
  
  @Test
  public void testHasStraight() {
    Card c14s = new Card("8", "hearts", 8, 8);
    Card c13s = new Card("5", "spades", 5, 5);
    Card c12s = new Card("6", "hearts", 6, 6);
    Card c11s = new Card("4", "clubs", 4, 4);
    Card c10s = new Card("7", "spades", 7, 7);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasStraight(myHand));
  }
  
  @Test
  public void testHasTriple() {
    Card c14s = new Card("Queen", "clubs", 10, 12);
    Card c13s = new Card("Queen", "spades", 10, 12);
    Card c12s = new Card("Queen", "hearts", 10, 12);
    Card c11s = new Card("6", "clubs", 6, 6);
    Card c10s = new Card("7", "clubs", 7, 7);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasTriple(myHand));
  }
  
  @Test
  public void testHasTwoPairs() {
    Card c14s = new Card("2", "clubs", 2, 2);
    Card c13s = new Card("Ace", "diamonds", 11, 14);
    Card c12s = new Card("4", "spades", 4, 4);
    Card c11s = new Card("Ace", "hearts", 11, 14);
    Card c10s = new Card("4", "hearts", 4, 4);
    
    myHand.addCard(c14s);
    myHand.addCard(c13s);
    myHand.addCard(c12s);
    myHand.addCard(c11s);
    myHand.addCard(c10s);
    
    assertTrue(PokerHandEvaluator.hasTwoPairs(myHand));
  }
}
