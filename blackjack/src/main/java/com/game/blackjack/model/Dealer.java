package com.game.blackjack.model;
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
        return handValue < 17;
    }

    public Card showFirstCard() {
        ArrayList<Card> hand = getHand();
        return hand.isEmpty() ? null : hand.get(0);
    }
}