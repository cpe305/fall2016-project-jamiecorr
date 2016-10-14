package com.corr.casino_maven;

import java.util.ArrayList;
import java.util.Random;

public class Deck 
{	
	private ArrayList<Card> deck;
	
    public Deck()
    {
    	this.createDeck();
    }
   
    public void createDeck()
    {
        ArrayList<Card> setDeck = new ArrayList<Card>();
        
        for (int i = 2; i <= 14; i++)
        {
            setDeck.add(new Card("clubs", i));
            setDeck.add(new Card("diamonds", i));
            setDeck.add(new Card("hearts", i));
            setDeck.add(new Card("spades", i));       
        }
        
        deck = setDeck;
    }
    
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
    	for (int i = 0; i < 52; i++)
		{
    		System.out.println(deck.get(i).toString());
			System.out.println();
		}
    }
    
	public void print(String statement)
    {
		
    }
}