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
        r = rank;
        s = suit;
    }
	
	//method
	public Card draw()
	{
		Card pick = new Card();
		return pick;
	}
	
}
