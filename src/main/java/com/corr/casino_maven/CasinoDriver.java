package com.corr.casino_maven;

import java.util.Scanner;

public class CasinoDriver {
	public static final void main(String[] args) {
		double startingBalance = 100;
		Bank playersBank = new Bank(startingBalance);
	     
		System.out.println("Do you want to play in the casino? (yes or no)");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
       
		while (input.equals("yes"))
		{
           System.out.println("You have $" + playersBank.getCurrentBalance()+ "Press 1 for Blackjack or 2 for Poker");
           int gameChoice = scan.nextInt();
           
           if (gameChoice == 1)
           {
               Blackjack.play(playersBank);
           }      
           else if (gameChoice == 2)
           {
               Poker.play(playersBank);
           }
           
           if (!playersBank.isBroke())
           {
               System.out.println("Do you want to stay in the casino?");
               input = scan.nextLine();
           }
           else
           {
               System.out.println("No money left, now exiting casino.");
        	   break;
           }
		}
	       
		System.out.println("---exit---");
		scan.close();
	}
}

