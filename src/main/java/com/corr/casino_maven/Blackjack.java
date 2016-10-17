package com.corr.casino_maven;

import java.util.Scanner;

public class Blackjack
{
	static Hand playersHand = new Hand("Player");
	static Hand dealersHand = new Hand("Dealer");  
	static boolean isStillPlaying = true;
	static double bet;
	final static double DECK_MINIMUM = 15,  BET_INCREASE = 1.5, MIN_HIT = 16;
	static final int TWENTY_ONE = 21;
	static String usersTurnPrompt = "Press 'n' for next card, 's' to stand, 'd' to double down";

	public static void play()
	{
		Scanner scan = new Scanner(System.in);
		
		Deck currentDeck = new BlackjackDeck();
              
		System.out.println("New Hand?");
		String input = scan.nextLine();
       
		while (input.equals("yes"))
		{
			//reset deck if necessary
			if (currentDeck.getSize() <= DECK_MINIMUM)
			{
				currentDeck = new BlackjackDeck();
			}
           			
			dealCards(currentDeck);
			
			System.out.println("2 cards dealt to each player, " + currentDeck.getSize() + " more Card in the deck");
			System.out.println("How much do you want to bet this round?");
			bet = scan.nextDouble();
	              
			System.out.println("Players cards:  " + playersHand.getCard(0) + " and " + playersHand.getCard(1));
			System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");
           
			if (didWinBlackjack(playersHand))
			{
				break;
			}
 
			System.out.println(usersTurnPrompt);
			String decision = scan.nextLine();
           
			while (decision.equals("n"))
			{
				Card newCard = currentDeck.drawRandomCard();
				playersHand.addCard(newCard);

				System.out.println("New card:  " + newCard);
                                     
				if (didLoseBlackjack(playersHand))
				{
					break;
				}
				else
				{
					System.out.println("You have:  " + playersHand.printHand());
					System.out.println(usersTurnPrompt);
					decision = scan.nextLine();
				}
			}
                
			if (decision.equals("d"))
			{
				bet += bet;
				System.out.println("Now betting $"+ bet);
                    
				Card newCard = currentDeck.drawRandomCard();
				playersHand.addCard(newCard);
                    
				System.out.println("New Card:  " + newCard);
                        
				if (didLoseBlackjack(playersHand))
				{
					break;
				}
				else
				{
					System.out.println(playersHand.printHand());
				}
			}
                
                
			if (isStillPlaying)
			{
				System.out.println("Dealer's other card:  " + dealersHand);
                    
				//dealer has to hit if 16 or less
				while (BlackjackHandModifier.getHandValue(dealersHand) <= MIN_HIT)
				{
					Card newCard = currentDeck.drawRandomCard();
					dealersHand.addCard(newCard);
					System.out.println("Dealer's new card:  " + newCard);
                        
					if (didLoseBlackjack(dealersHand))
					{
						System.out.println("Dealer busted");
						CasinoDriver.playersBank.addMoney(bet);
						isStillPlaying = false;
						break;
					}
					System.out.println("Dealer's hand:  "+ dealersHand.printHand());
				}
			}
			
//****************
//****************
                
			if (isStillPlaying)
			{
				if (BlackjackHandModifier.getHandValue(playersHand) != BlackjackHandModifier.getHandValue(dealersHand))
				{
					System.out.println("The dealer stays. You have:  " + playersHand.printHand() + "\n\n" + getWinnerName() + " won");
					if (getWinnerName().equals("Dealer"))
					{
						CasinoDriver.playersBank.subtractMoney(bet);
					}
					else
					{
						CasinoDriver.playersBank.addMoney(bet);
					}
				}
				else
				{
					System.out.println("Tie");
				}
			}
                
			System.out.println(CasinoDriver.playersBank);
                
			if (CasinoDriver.playersBank.isBroke())
			{
				System.out.println("No more money");
				break;
			}
                
			System.out.println("New hand?");
			input = scan.nextLine();
		}
   
		scan.close();
	}
	
	public static void dealCards(Deck thisDeck)
	{
		Card playersCard1 = thisDeck.drawRandomCard();
		Card playersCard2 = thisDeck.drawRandomCard();
		playersHand.addCard(playersCard1);
		playersHand.addCard(playersCard2);
          
		Card dealersCard1 = thisDeck.drawRandomCard(); 
		Card dealersCard2 = thisDeck.drawRandomCard();
		dealersHand.addCard(dealersCard1);
		dealersHand.addCard(dealersCard2);
	}
	
	public static String getWinnerName()
    {
		int pValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(playersHand);
		int dValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(dealersHand);
        if (pValue > dValue)
        {
            return dealersHand.getPlayerName();
        }
        else
        {
            return playersHand.getPlayerName();
        }
    }
	
	public static boolean didWinBlackjack(Hand hand)
	{
		//if you have 21 to start you win 1.5 times your bet
		if(BlackjackHandModifier.getHandValue(hand) == TWENTY_ONE)
		{
			CasinoDriver.playersBank.addMoney(bet * BET_INCREASE);
			System.out.println("Blackjack to start! Wow!");
			isStillPlaying = false;
			return true;
		}
		return false;
	}
	
	public static boolean didLoseBlackjack(Hand hand)
	{
		if (BlackjackHandModifier.getHandValue(hand) > TWENTY_ONE)
		{
			System.out.println("--bust--");
			CasinoDriver.playersBank.subtractMoney(bet);
			isStillPlaying = false;
			return true;
		}
		return false;
	}
}