package com.game.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck 
{
	private List<Card> cardDeck = new ArrayList<>();
	
	public Deck()
	{
		createDeck();
	}
	
	public void createDeck()
	{
		cardDeck.clear();
		String[] suits = {"h", "d", "c", "s"};
		for (String suit : suits) {
			for (int rank = 1; rank <= 13; rank++) {
				cardDeck.add(new Card(rank, suit));
			}
		}
	}
	
	public void shuffle()
	{
		Collections.shuffle(cardDeck);
	}

	public Card drawCard() 
	{
		if (cardDeck.isEmpty()) return null;
    	return cardDeck.remove(0); // remove top card
	}

	public int getRemainingCards() 
	{
		return cardDeck.size();
	}
}
