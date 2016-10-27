package com.corr.casino_maven;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.casino.Card;
import edu.calpoly.cpe305.casino.Hand;

public class TestHand {
	Hand h = new Hand("Jamie");
	
	@Test
	public void testGetPlayerName() {
		assertEquals("Jamie", h.getPlayerName());
	}
	
	@Test
	public void testGetCardInPosition() {
		Card c = new Card("Two", "clubs", 5);
		h.addCard(c);
		
		assertEquals(c, h.getCard(0));
	}
}
