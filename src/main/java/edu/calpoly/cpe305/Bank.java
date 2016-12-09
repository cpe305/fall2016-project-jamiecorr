package edu.calpoly.cpe305;

import java.math.BigDecimal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bank {
  private BigDecimal money;

  private final IntegerProperty sumOfChips = new SimpleIntegerProperty(1000);
  private final IntegerProperty roundBet = new SimpleIntegerProperty();
  private static Bank instance;


  public Bank(BigDecimal money) {
    this.money = money;
  }
  
  public Bank() {
  }

  public void addMoney(BigDecimal amount) {
    money = money.add(amount);
  }

  public void subtractMoney(BigDecimal amount) {
    money = money.subtract(amount);
  }

  public boolean isBroke() {
    if (money.compareTo(BigDecimal.ZERO) > 0) {
      return false;
    }
    return true;
  }

  public BigDecimal getCurrentBalance() {
    return money;
  }

  public String printCurrentBalance() {
    return "You have $" + money;
  }
  
  /**
   * Returns the value of the roundBet.
   *
   * @return the value of the roundBet
   */
  public int getRoundBet() {
      return roundBet.get();
  }

  /**
   * Sets the value of roundBet.
   *
   * @param roundBet is the value that will be set.
   */
  public void setRoundBet(int roundBet) {
      this.roundBet.set(roundBet);
  }

  /**
   * Sets the value of sumOfChips.
   *
   * @param sumOfChips is the value that will be set.
   */
  public void addChips(int sumOfChips) {
      this.sumOfChips.set(this.sumOfChips.get() + sumOfChips);
  }

  /**
   * Sets the value of sumOfChips.
   *
   * @param sumOfChips is the value that will be set.
   */
  public void subChips(int sumOfChips) {
      this.sumOfChips.set(this.sumOfChips.get() - sumOfChips);
  }
  
  /**
   * Returns the value of sumOfChips.
   *
   * @return the value of sumOfChips
   */
  public int getSumOfChips() {
      return sumOfChips.get();
  }
  
  /**
   * Implementing the Singleton get instance function
   * @return the TileSet object 
   */
  public static Bank getInstance() {
    if (instance == null) {
      instance = new Bank();
    }
    return instance;
  }
}
