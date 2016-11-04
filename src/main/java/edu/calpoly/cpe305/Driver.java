package edu.calpoly.cpe305;

public class Driver {
   public static void main(String args[]) {
      Game game;
      Observer mainGame;
      // if you want to make it so that it changes whether to be a console
      // or gui when running the main method through command line
      // -c for console, -g for gui
      String type = "-c";
      
      //Remaining comments are potential code to use, unsure of what to do for this
      //the menu object should be of a class with similar functionality to
      //CasinoDriver, where calling selectGame would prompt the user to
      //what type of game they want to play, getGame() returns the Game
      
      //CasinoDriver menu = new CasinoDriver();
      //menu.selectGame();
      
      if (type.equals("-g")) {
    	//mainGame = new Main(menu.getGame());
      }
      else {
    	//mainGame = new ConsoleMain(menu.getGame());
      }
      
      //game.addObserver(mainGame);
      //mainGame.update(game);
      
   }
}
