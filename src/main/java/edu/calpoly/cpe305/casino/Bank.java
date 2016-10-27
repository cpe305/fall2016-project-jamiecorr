package edu.calpoly.cpe305.casino;

public class Bank {
  private double money;

  public Bank(double money) {
    this.money = money;
  }

  public void addMoney(double amount) {
    money += amount;
  }

  public void subtractMoney(double amount) {
    money -= amount;
  }

  public boolean isBroke() {
    return money <= 0;
  }

  public double getCurrentBalance() {
    return money;
  }

  public String printCurrentBalance() {
    return "You have $" + money;
  }
}
