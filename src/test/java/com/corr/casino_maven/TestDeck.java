package com.corr.casino_maven;

import static org.junit.Assert.*;

import org.junit.*;

public class TestDeck
{
	Deck d = new Deck();
	
	@Test
	public void testGetDeckSize() {
		assertEquals(52, d.getSize());
	}
	
	@Test
	public void testGetCardInPosition() {
		assertEquals("clubs", d.getCardInPosition(0).getSuit());
	}
}