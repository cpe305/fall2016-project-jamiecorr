package edu.calpoly.cpe305.test;

import edu.calpoly.cpe305.Bank;


import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;


public class TestBank {
  BigDecimal startingBalance = new BigDecimal(199.99);
  Bank bank = new Bank(startingBalance);

  @Test
  public void testGetCurrentBalance() {
    assertEquals(new BigDecimal(199.99), bank.getCurrentBalance(), true);
  }

 
  @Test
  public void testPrintCurrentBalance() {
    Assert.assertTrue(bank.printCurrentBalance().equals("You have $199.99"));
  }

  @Test
  public void testAdd() {
    bank.addMoney(new BigDecimal(20.75));
    assertEquals(new BigDecimal(220.74), bank.getCurrentBalance(), true);
  }

  @Test
  public void testSub() {
    bank.subtractMoney(new BigDecimal(99.01));
    assertEquals(new BigDecimal(100.98), bank.getCurrentBalance(), true);
  }

  @Test
  public void testIsBroke() {
    bank.subtractMoney(new BigDecimal(199.99));
    assertTrue(bank.isBroke());
  }
  
  public static void assertEquals(BigDecimal expected, BigDecimal actual, boolean precisionMatters) {
    if (precisionMatters) {
      Assert.assertEquals(expected, actual);
    } else {
      Assert.assertEquals(0, expected.compareTo(actual));
    }
  }

}
