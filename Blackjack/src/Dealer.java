import java.util.*;

public class Dealer extends Player 
{
    public Dealer() {
        super("Dealer");
    }

    // Dealer specific behavior can be added here
    public boolean shouldHit() {
        // Dealer must hit on 16 and below, stand on 17 and above
        int handValue = getTotalPoints();
        System.out.println("Dealer's hand value: " + handValue);
        return handValue < 17;
    }

    public void showFirstCard() {
        ArrayList<Card> hand = getHand();
        // Show only the first card of the dealer's hand
        if (!hand.isEmpty()) {
            System.out.println("Dealer's first card: " + hand.get(0).getValue() + hand.get(0).getSuit());
        }
    }

    public void showSecondCard() {
        ArrayList<Card> hand = getHand();
        // Show only the first card of the dealer's hand
        if (!hand.isEmpty()) {
            System.out.println("Dealer's second card: " + hand.get(1).getValue() + hand.get(1).getSuit());
        }
    }

    @Override
    public boolean playLogic() {
        if(shouldHit()) {
            return true; // Dealer will draw a card
        }
        return false; // Dealer will stand
    }
}