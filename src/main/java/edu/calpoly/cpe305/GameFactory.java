package edu.calpoly.cpe305;

public class GameFactory {
  public Deck getDeck(String deckType){
    if(deckType == null){
      return null;
    }
    
    if("BLACKJACK".equalsIgnoreCase(deckType)){
      return new BlackJackDeck(1);
    } else if("POKER".equalsIgnoreCase(deckType)){
      return new PokerDeck(2);
    }
    
    return null;
  }
}