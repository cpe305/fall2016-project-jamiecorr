public class Card
{
    private String suit;
    private int value;
    private int position;
   
    public Card(String a, int b, int c)
    {
        suit = a;
        value = b;
        position = c;
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
}