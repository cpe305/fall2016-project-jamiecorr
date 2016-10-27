package edu.calpoly.cpe305;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.calpoly.cpe305.Bank;

public class TestBank 
{
	double startingBalance = 199.99;
	Bank bank = new Bank(startingBalance);
	
	@Test
	public void testGetCurrentBalance()
	{
		assertEquals(199.99, bank.getCurrentBalance(), 0);
	}
	
	@Test
	public void testPrintCurrentBalance()
	{
		assertEquals("You have $199.99", bank.printCurrentBalance());
	}
	
	@Test
	public void testAdd()
	{
		bank.addMoney(20.75);
		assertEquals(220.74, bank.getCurrentBalance(), 0);
	}
	
	@Test
	public void testSub()
	{
		bank.subtractMoney(99.01);
		assertEquals(100.98, bank.getCurrentBalance(), 0);
	}
	
	@Test
	public void testIsBroke()
	{
		bank.subtractMoney(199.99);
		assertTrue(bank.isBroke());
	}
}
