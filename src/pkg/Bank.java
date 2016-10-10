package pkg;
public class Bank
{
    private double money;
    
    public Bank(double money)
    {
    	this.money = money;
    }
     
    public void addMoney(double amount)
    {
    	money += amount;
    }
     
    public void subtractMoney(double amount)     
    {
    	money -= amount;
    }

}