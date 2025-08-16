public class Card 
{
    private int rank;
    private String suit;

    private Card() 
    {
		suit = "";
		rank = 0;
	}
    public Card(int r, String s) 
    {
        rank = r;
        suit = s;
    }
    public int getValue() 
    {
        return rank;
    }
    public String getSuit() 
    {
        return suit;
    }
}
