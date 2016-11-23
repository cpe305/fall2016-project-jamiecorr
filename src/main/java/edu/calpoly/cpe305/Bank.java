package edu.calpoly.cpe305;

import java.math.BigDecimal;

public class Bank {
  private BigDecimal money;

  public Bank(BigDecimal money) {
    this.money = money;
  }

  public void addMoney(BigDecimal amount) {
    money = money.add(amount);
  }

  public void subtractMoney(BigDecimal amount) {
    money = money.subtract(money);
  }

  public boolean isBroke() {
    if (money.compareTo(BigDecimal.ZERO) > 0) {
      return true;
    }
    return false;
  }

  public BigDecimal getCurrentBalance() {
    return money;
  }

  public String printCurrentBalance() {
    return "You have $" + money;
  }
}
