package pkg;
import java.util.ArrayList;

public class Hand implements Printer
{
    private String name;
    private ArrayList<Card> hand;
    
    public Hand(String name)
    {
        hand = new ArrayList<Card>();
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Card getCardInPosition(int i)
    {
        return hand.get(i);
    }
    
    public void addCard(Card card)
    {
        hand.add(card);
    }
    
	public void print(String statement)
	{
		
	}
}