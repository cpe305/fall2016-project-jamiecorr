package edu.calpoly.cpe305;

import java.io.Writer;
import java.io.PrintWriter;

public class ConsoleMain implements Observer{

	private Subject subject;
	private Writer writer;
	
	public ConsoleMain(Subject subject) {
		this.subject = subject;
		// default writer is System.out, need to manually change it for tests
		writer = new PrintWriter(System.out);
	}
	
	/*
	 * For Tests, use a custom PrintWriter instead of System.out
	 */
	public void setWriter(Writer out) {
		writer = out;
	}
	
	@Override
	public void update(Subject subject) {
		if (subject instanceof Game) {
			Game game = (Game)subject;
			// can change in final implementation to whatever you want to print out
			try {
				writer.write(game.getInfo());
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
		}
	}

}
