package com.game.blackjack.model;
import java.util.*;

public class Player 
{
	private int points = 0;
	private ArrayList<Card> hand;
	private String name;
	
	public Player(String name) 
	{
		this.name = name;
		hand = new ArrayList<Card>();
		calculatePoints();
	}

	public String getName() 
	{
		return name;
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

	public void clearHand()
	{
		hand.clear();
		points = 0;
	}
	
	public boolean hasBlackjack()
	{
		return points == 21;
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
}