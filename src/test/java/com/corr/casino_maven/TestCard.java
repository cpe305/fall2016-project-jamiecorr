package com.corr.casino_maven;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestCard
{
	Card card = new Card("Two", "clubs", 5);

	@Test
	public void testGetSuit()
	{
		assertEquals("clubs", card.getSuit());
	}
	
	@Test
	public void testGetValue()
	{
		assertEquals(5, card.getValue());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Two of clubs", card.toString());
	}
}