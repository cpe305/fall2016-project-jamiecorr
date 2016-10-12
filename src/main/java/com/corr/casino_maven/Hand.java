package com.corr.casino_maven;
import java.util.ArrayList;

public class Hand implements Printer
{
    private String playerName;
    private ArrayList<Card> hand;
    
    public Hand(String playerName)
    {
        hand = new ArrayList<Card>();
        this.playerName = playerName;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }
    
    public Card getCard(int i)
    {
        return hand.get(i);
    }
    
    public void addCard(Card card)
    {
        hand.add(card);
    }
    
	public void print(String statement)
	{
		
	}
}