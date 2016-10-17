package com.corr.casino_maven;
public class Card
{
    private String name, suit;
    private int value;
   
    public Card(String name, String suit, int value)
    {
    	this.name = name;
        this.suit = suit;
        this.value = value;
    }
   
    public String getName()
    {
        return name;
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
        return name + " of " + suit;
    }
}