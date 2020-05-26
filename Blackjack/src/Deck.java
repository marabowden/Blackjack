public class Deck 
{
	Card [][] cardDeck = new Card[4][13];
	
	public Deck(Card [][] d)
	{
		cardDeck=d;
	}
	
	public void createDeck()
	{
		for (int rank = 1; rank < 14; rank++ )   
		{
			cardDeck[0][rank-1]=new Card(rank, "h");
		}
		for (int rank = 1; rank < 14; rank++ )   
		{
			cardDeck[1][rank-1]=new Card(rank, "d");
		}
		for (int rank = 1; rank < 14; rank++ )   
		{
			cardDeck[2][rank-1]=new Card(rank, "c");
		}
		for (int rank = 1; rank < 14; rank++ )   
		{
			cardDeck[3][rank-1]=new Card(rank, "s");
		}
	}
	public void remove()
	{
		int count=0;
		while(count>40)
		{
			int x = (int)(Math.random()*4);
			int y = (int)(Math.random()*52);
			if(cardDeck[x][y]!=null)
			{
				cardDeck[x][y]=null;
				count++;
			}
			else 
			{
				continue;
			}
		}
		createDeck();
	}
	public void printDeck()
	{
		for (int suit = 0; suit < 4; suit++ )   
		{
			for ( int rank = 1; rank <= 13; rank++ )
			{
				System.out.print(cardDeck[rank][suit]);
			}
		}
	}
}
