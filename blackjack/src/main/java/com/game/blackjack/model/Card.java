package com.game.blackjack.model;

public class Card 
{
    private int rank;
    private String suit;

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

    @Override
    public String toString() {
        String rankStr;
        switch (rank) {
            case 1 -> rankStr = "A";
            case 11 -> rankStr = "J";
            case 12 -> rankStr = "Q";
            case 13 -> rankStr = "K";
            default -> rankStr = String.valueOf(rank);
        }
        return rankStr + suit; // e.g., "As", "10d"
}
}
