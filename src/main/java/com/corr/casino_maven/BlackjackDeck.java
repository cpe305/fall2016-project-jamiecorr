package com.corr.casino_maven;

import java.util.ArrayList;

public class BlackjackDeck extends Deck 
{	
	public enum Suits {
		CLUBS("clubs"),
		DIAMONDS("diamonds"),
		HEARTS("hearts"),
		SPADES("spades");

	    private final String text;

	    private Suits(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}
	
	Suits suits;
	private static final int MAX_CARD_VALUE = 11;
	
	public BlackjackDeck()
	{
		super();
		this.createDeck(deck);
	}

	public ArrayList<Card> createDeck(ArrayList<Card> myDeck)
	{
		myDeck = new ArrayList<Card>();
		
		for (int value = 0; value < MAX_CARD_VALUE; value++)
	    {
			setValues(Suits.CLUBS.toString());
			setValues(Suits.DIAMONDS.toString());
			setValues(Suits.HEARTS.toString());
			setValues(Suits.SPADES.toString());
	    }
		
	    return myDeck;
	}
	
	public void setValues(String suitChoice)
	{
		for (int value = 2; value <= MAX_CARD_VALUE; value++)
		{
			switch (value)
		    {
		        case 2:
		            deck.add(new Card("Two", suitChoice, value));
		        case 3:
		        	deck.add(new Card("Three", suitChoice, value));
		        case 4:
		        	deck.add(new Card("Four", suitChoice, value));
		        case 5:
		        	deck.add(new Card("Five", suitChoice, value));
		        case 6:
		        	deck.add(new Card("Six", suitChoice, value));
		        case 7:
		        	deck.add(new Card("Seven", suitChoice, value));
		        case 8:
		        	deck.add(new Card("Eight", suitChoice, value));
		        case 9:
		        	deck.add(new Card("Nine", suitChoice, value));
		        case 10:
		        	deck.add(new Card("Ten", suitChoice, value));
		        	deck.add(new Card("Jack", suitChoice, value));
		        	deck.add(new Card("Queen", suitChoice, value));
		        	deck.add(new Card("King", suitChoice, value));
		        case 11:
		        	deck.add(new Card("Ace", suitChoice, value));
		    }  
		}
	}
}
