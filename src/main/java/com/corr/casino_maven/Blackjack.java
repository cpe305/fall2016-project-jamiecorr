package com.corr.casino_maven;


public class Blackjack
{
	static Hand playersHand, dealersHand;
	static boolean isStillPlaying = true;
	static double bet;
	static final double BET_INCREASE = 1.5;
	static final int TWENTY_ONE = 21, DECK_MINIMUM = 15, MIN_HIT = 16;
	static String decision, input = "yes", usersTurnPrompt = "Press 'n' for next card, 's' to stand, 'd' to double down";

	public static void play()
	{
		Deck currentDeck = new Deck(CasinoDriver.CHOICE1);
       
		while (true)
		{
			//reset deck if necessary
			if (currentDeck.getSize() <= DECK_MINIMUM)
			{
				currentDeck = new Deck(CasinoDriver.CHOICE1);
			}
           			
			dealCards(currentDeck);
			
			System.out.println("How much do you want to bet this round?");
			bet = Double.parseDouble(CasinoDriver.scan.nextLine());
			
			System.out.println("Players cards:  " + playersHand.getCard(0) + " and " + playersHand.getCard(1));
			System.out.println("Dealers cards:  " + dealersHand.getCard(1) + " and another card");
           
			if (didWinNaturally(playersHand))
			{
				break;
			}
 
			while (isStillPlaying)
			{
				System.out.println(usersTurnPrompt);
				decision = CasinoDriver.scan.nextLine();
				
				if (decision.equals("n"))
					dealNextCard(currentDeck);
				else if (decision.equals("d"))
				{
					bet += bet;
					System.out.println("Now betting $"+ bet + "...");
					dealNextCard(currentDeck);
				}
				else if (decision.equals("s"))
				{                    
					//dealer has to hit if 16 or less
					if (BlackjackHandModifier.getHandValue(dealersHand) <= MIN_HIT)
					{
						Card newCard = currentDeck.drawRandomCard();
						dealersHand.addCard(newCard);
					}
					System.out.println("Dealer's hand:  "+ dealersHand.printHand());

					if (didLoseBlackjack(dealersHand))
					{
						System.out.println("Dealer busted");
						CasinoDriver.playersBank.addMoney(bet);
						System.out.println("$" + bet + "won");
						isStillPlaying = false;
					}
				}
			}
                
			endGame();
                                
			if (CasinoDriver.playersBank.isBroke())
			{
				System.out.println("No more money");
				break;
			}
                
			System.out.println("New hand?");
			input = CasinoDriver.scan.nextLine();
			if (input.equals("no"))
				break;
			else
				isStillPlaying = true;
		}
	}
	

	private static void dealNextCard(Deck currentDeck) 
	{
		Card newCard = currentDeck.drawRandomCard();
		playersHand.addCard(newCard);
            
		System.out.println("New Card:  " + newCard);
                
		if (didLoseBlackjack(playersHand))
		{
			System.out.println("You lost with hand value: " + BlackjackHandModifier.getHandValue(playersHand));
			isStillPlaying = false;
		}
		else
		{
			System.out.println("You have:  " + playersHand.printHand());
			System.out.println(usersTurnPrompt);
			decision = CasinoDriver.scan.nextLine();
		}	
	}


	private static void dealCards(Deck thisDeck)
	{
		playersHand = new Hand("Player");
		dealersHand = new Hand("Dealer");
		
		Card playersCard1 = thisDeck.drawRandomCard();
		Card playersCard2 = thisDeck.drawRandomCard();
		playersHand.addCard(playersCard1);
		playersHand.addCard(playersCard2);
          
		Card dealersCard1 = thisDeck.drawRandomCard(); 
		Card dealersCard2 = thisDeck.drawRandomCard();
		dealersHand.addCard(dealersCard1);
		dealersHand.addCard(dealersCard2);
		
		System.out.println("2 cards dealt to each player, " + thisDeck.getSize() + " more cards in the deck");
	}
	
	private static String getWinnerName()
    {
		int pValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(playersHand);
		int dValue = TWENTY_ONE - BlackjackHandModifier.getHandValue(dealersHand);
		
        return pValue > dValue ? dealersHand.getPlayerName() : playersHand.getPlayerName();
    }
	
	private static boolean didWinNaturally(Hand hand)
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
	
	private static boolean didLoseBlackjack(Hand hand)
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
	
	private static void endGame() 
	{
		if (BlackjackHandModifier.getHandValue(playersHand) != BlackjackHandModifier.getHandValue(dealersHand))
		{
			if (getWinnerName().equals("Dealer"))
			{
				CasinoDriver.playersBank.subtractMoney(bet);
				System.out.println("Subtracting $" + bet + "from bank");
			}
			else
			{
				CasinoDriver.playersBank.addMoney(bet);
				System.out.println("Adding $" + bet + "from bank");
			}
		}
		else
		{
			System.out.println("Tie");
		}
	}
}