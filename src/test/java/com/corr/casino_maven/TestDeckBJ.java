package com.corr.casino_maven;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeckBJ {
	Deck deck = new Deck(1);
	
	@Test
	public void testSize() {
		assertEquals(52, deck.getSize());
	}
	
	@Test
	public void testCreateDeck() {
		assertEquals("Two of clubs", deck.getCardInPosition(0).toString());
		assertEquals("Three of diamonds", deck.getCardInPosition(14).toString());
		assertEquals("King of hearts", deck.getCardInPosition(37).toString());
		assertEquals("Ace of spades", deck.getCardInPosition(51).toString());
	}
}