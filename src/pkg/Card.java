package pkg;
public class Card implements Printer
{
    private String suit;
    private int value;
    private int position;
   
    public Card(String suit, int value, int position)
    {
        this.suit = suit;
        this.value = value;
        this.position = position;
    }
   
    public String getSuit()
    {
        return suit;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getPosition()
    {
        return position;
    }
    
    public void print(String statement)
    {
    
    }
}