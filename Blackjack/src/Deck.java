public class Deck 
{
	private Card[][] cardDeck = new Card[4][13];
	
	public Deck()
	{
		createDeck();
	}
	
	public void createDeck()
	{
		for (int suit = 0; suit < 4; suit++)   
		{
			for (int rank = 0; rank < 13; rank++)
			{
				String suitStr = suit == 0 ? "h" : suit == 1 ? "d" : suit == 2 ? "c" : "s";
				cardDeck[suit][rank] = new Card(rank + 1, suitStr);
			}
		}
	}
	
	public void shuffle()
	{
		for (int suit = 0; suit < 4; suit++)
		{
			for (int rank = 0; rank < 13; rank++)
			{
				int randSuit = (int)(Math.random() * 4);
				int randRank = (int)(Math.random() * 13);
				Card temp = cardDeck[suit][rank];
				cardDeck[suit][rank] = cardDeck[randSuit][randRank];
				cardDeck[randSuit][randRank] = temp;
			}
		}
	}
	
	public Card getCard(int suit, int rank)
	{
		return cardDeck[suit][rank];
	}
	
	public void printDeck()
	{
		for (int suit = 0; suit < 4; suit++)   
		{
			for (int rank = 0; rank < 13; rank++)
			{
				if (cardDeck[suit][rank] != null)
				{
					System.out.print(cardDeck[suit][rank].getValue() + cardDeck[suit][rank].getSuit() + " ");
				}
			}
			System.out.println();
		}
	}

	public Card drawCard() 
	{
		for (int suit = 0; suit < 4; suit++)   
		{
			for (int rank = 0; rank < 13; rank++)
			{
				if (cardDeck[suit][rank] != null)
				{
					Card drawnCard = cardDeck[suit][rank];
					cardDeck[suit][rank] = null; // Remove card from deck
					return drawnCard;
				}
			}
		}
		return null; // No cards left to draw
	}
}
