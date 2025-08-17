package com.game.blackjack.service;

import com.game.blackjack.model.Game;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {

    private Game blackjack;

    public String startGame() {
        blackjack = new Game();
        blackjack.startGame();
        return "Game started! Player and dealer have been dealt cards.";
    }

    public String playerHit() {
        if (blackjack != null) {
            blackjack.playerHit();
            if (blackjack.getPlayer().isBusted()) {
                return "Player busted! Dealer wins.";
            }
            return "Player hits!";
        }
        return "No active game!";
    }

    public String playerStand() {
        blackjack.playerStand();
        return "Player stands. Dealer's turn.";
    }

    public String dealerDrawNextCard() {
        blackjack.dealerDrawNextCard();
        return "Dealer draws a card.";
    }

    public Game getGame() {
        return blackjack;
    }
}
