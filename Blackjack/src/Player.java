import java.util.*;

public class Player 
{
	private int points;
	private ArrayList<Card> hand;
	private String name;
	
	public Player(String name) 
	{
		this.name = name;
		hand = new ArrayList<Card>();
		calculatePoints();
	}
	{
		points = 0;
		hand = new ArrayList<Card>();
	}

	public void addACard(Card card)
	{
		hand.add(card);
		calculatePoints(); 
	}

	public int getTotalPoints()
	{
		calculatePoints();
		return points;
	}
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}

	public void printHand()
	{
		System.out.print(name + "'s hand: ");
		for (Card card : hand) {
			System.out.print(card.getValue() + card.getSuit() + " ");
		}
		System.out.println();
	}

	public void clearHand()
	{
		hand.clear();
		points = 0;
	}
	
	public boolean hasBlackjack()
	{
		return hand.size() == 2 && points == 21;
	}

	public boolean isBusted()
	{
		return points > 21;
	}
	
	public void calculatePoints()
	{
		points = 0;
		int aces = 0;
		for(Card card : hand) {
			if(card.getValue() == 1) {
				aces++;
			}
			points += Math.min(10, card.getValue());
		}
		while(aces > 0 && points + 10 <= 21) {
			points += 10;
			aces--;
		}
	}
	public boolean playLogic() {
		Scanner scanner = new Scanner(System.in);
		while (!isBusted()) {
			System.out.println(name + "'s turn. Current hand:");
			printHand();
			System.out.println("Total points: " + points);
			System.out.print("Would you like to hit? (y/n): ");
			String input = scanner.nextLine().toLowerCase();
			
			if (input.equals("n")) {
				return false; // Return to let the game continue without drawing a card
			} else if (input.equals("y")) {
				return true;  // Return to let the game deal another card
			} else {
				System.out.println("Invalid input. Please enter 'y' or 'n'");
			}
		}
		return false;
	}
}