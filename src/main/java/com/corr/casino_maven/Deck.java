package com.corr.casino_maven;

import java.util.ArrayList;
import java.util.Random;

public abstract class Deck 
{	
	private ArrayList<Card> deck;
	
    public int getSize()
    {
        return deck.size();
    }
    
    public Card getCardInPosition(int ndx)
    {
    	return deck.get(ndx);
    }
    
    public Card drawRandomCard()
    {
        Random generator = new Random();
        int cardValue = generator.nextInt(deck.size());
        Card test = deck.get(cardValue);
        
        deck.remove(cardValue);
        return test;
    }
    
    public void printCards()
    {
    	for (int i = 0; i < deck.size(); i++)
		{
    		System.out.println(deck.get(i).toString());
			System.out.println();
		}
    }
    
	public void print(String statement)
    {
		
    }
	
	abstract ArrayList<Card> createDeck();
}