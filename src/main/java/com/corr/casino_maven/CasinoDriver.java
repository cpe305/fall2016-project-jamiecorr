package com.corr.casino_maven;

import java.util.Scanner;

public class CasinoDriver {
	static final double BALANCE = 100;
	static final int CHOICE1 = 1, CHOICE2 = 2, CHOICE3 = 3;
	static Bank playersBank = new Bank(BALANCE);
	static int gameChoice;
	
	public static final void main(String[] args) 
	{     
		System.out.println("Do you want to play in the casino? (yes or no)");
		Scanner scan = new Scanner(System.in);

		String input = scan.nextLine();

		while (input.equals("yes"))
		{
           System.out.println("You have $" + playersBank.getCurrentBalance()+ "  Press 1:Blackjack or 2:Poker");
           gameChoice = scan.nextInt();
          
           if (gameChoice == 1)
           {
               Blackjack.play();
           }      
           else if (gameChoice == 2)
           {
               Poker.play(playersBank);
           }
           
           if (!playersBank.isBroke())
           {
        	   	System.out.println("Do you want to stay in the casino?");
        	   	input = scan.nextLine();
        	   	//TODO: fix scanner bug
           }
           else
           {
               System.out.println("No money left, now exiting casino.");
        	   break;
           }
		}
	       
		System.out.println("---exit---");
	}
}

