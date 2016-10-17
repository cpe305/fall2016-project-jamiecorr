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
	ArrayList<Card> setDeck;
	private static final int MAX_CARD_VALUE = 11;
	
	public BlackjackDeck()
	{
		this.createDeck();
	}

	public ArrayList<Card> createDeck()
	{
		setDeck = new ArrayList<Card>();
		
		for (int value = 0; value < MAX_CARD_VALUE; value++)
	    {
			setValues(Suits.CLUBS.toString());
			setValues(Suits.DIAMONDS.toString());
			setValues(Suits.HEARTS.toString());
			setValues(Suits.SPADES.toString());
	    }
		
	    return setDeck;
	}
	
	public void setValues(String suitChoice)
	{
		for (int value = 2; value <= MAX_CARD_VALUE; value++)
		{
			switch (value)
		    {
		        case 2:
		            setDeck.add(new Card("Two", suitChoice, value));
		        case 3:
		            setDeck.add(new Card("Three", suitChoice, value));
		        case 4:
		            setDeck.add(new Card("Four", suitChoice, value));
		        case 5:
		            setDeck.add(new Card("Five", suitChoice, value));
		        case 6:
		            setDeck.add(new Card("Six", suitChoice, value));
		        case 7:
		            setDeck.add(new Card("Seven", suitChoice, value));
		        case 8:
		            setDeck.add(new Card("Eight", suitChoice, value));
		        case 9:
		            setDeck.add(new Card("Nine", suitChoice, value));
		        case 10:
		            setDeck.add(new Card("Ten", suitChoice, value));
		            setDeck.add(new Card("Jack", suitChoice, value));
		            setDeck.add(new Card("Queen", suitChoice, value));
		            setDeck.add(new Card("King", suitChoice, value));
		        case 11:
		            setDeck.add(new Card("Ace", suitChoice, value));
		        default:
		            System.out.println("Error at setValues");
		    }  
		}
	}
}
