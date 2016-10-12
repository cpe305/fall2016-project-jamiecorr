package com.corr.casino_maven;
public class Card implements Printer
{
    private String suit;
    private int value;
   
    public Card(String suit, int value)
    {
        this.suit = suit;
        this.value = value;
    }
   
    public String getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public String toString()
    {
        return "" + value + " of " + suit;
    }
    
    public void print(String statement)
    {
    
    }
}