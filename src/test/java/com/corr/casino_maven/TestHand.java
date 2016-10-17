package com.corr.casino_maven;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHand {
	Hand h = new Hand("Jamie");
	
	@Test
	public void testGetPlayerName() {
		assertEquals("Jamie", h.getPlayerName());
	}
	
	@Test
	public void getCardInPosition() {
		Card c = new Card("Two", "clubs", 5);
		h.addCard(c);
		
		assertEquals(c, h.getCard(0));
	}
}
