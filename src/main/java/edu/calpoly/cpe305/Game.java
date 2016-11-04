package edu.calpoly.cpe305;

// have the Blackjack and Poker implement this
public interface Game extends Subject{
	public String getInfo();
	public void play();
	//add more methods you want the observers to call
}
