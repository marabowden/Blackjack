package com.game.blackjack.model;

import java.util.List;
import java.util.stream.IntStream;

import com.game.blackjack.dto.GameStateResponse;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private boolean playerStands;
    private boolean dealerStands;

    public Game() {
        deck = new Deck();
        player = new Player("Player");
        dealer = new Dealer();
        playerStands = false;
        dealerStands = false;
    }

    public void startGame() {
        deck.shuffle();
        dealInitialCards();
    }

    private void dealInitialCards() {
        player.addACard(deck.drawCard());
        dealer.addACard(deck.drawCard());
        player.addACard(deck.drawCard());
        dealer.addACard(deck.drawCard());
    }

    // ----------- Player Actions -----------
    public void playerHit() {
        if (!playerStands && !player.isBusted()) {
            player.addACard(deck.drawCard());
        }
    }

    public void playerStand() {
        playerStands = true;
    }

    // ----------- Dealer Actions -----------
    public boolean dealerDrawNextCard() {
        if (!dealerStands && playerStands) { // dealer only plays after player stands
            if (dealer.shouldHit()) {
                dealer.addACard(deck.drawCard());
                if (!dealer.shouldHit()) {
                    dealerStands = true;
                }
                return true; // card drawn
            } else {
                dealerStands = true;
            }
        }
        return false; // no card drawn
    }

    public boolean isGameOver() {
        return player.isBusted() || dealer.isBusted() || (playerStands && dealerStands);
    }

    public int determineWinner() { // 0 = tie, 1 = player win, -1 = dealer win
        int playerPoints = player.getTotalPoints();
        int dealerPoints = dealer.getTotalPoints();

        if (player.isBusted()) {
            return -1;
        } else if (dealer.isBusted()) {
            return 1;
        } else if (player.hasBlackjack() && dealer.hasBlackjack()) {
            return 0;
        } else if (player.hasBlackjack()) {
            return 1;
        } else if (dealer.hasBlackjack()) {
            return -1;
        } else if (playerPoints > dealerPoints) {
            return 1;
        } else if (dealerPoints > playerPoints) {
            return -1;
        } else {
            return 0;
        }
    }

    // ----------- Getters for UI/API -----------
    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getStatus() {
        String status;
        if (getPlayer().isBusted()) {
            status = "dealer-won";
        } else if (getDealer().isBusted()) {
            status = "player-won";
        } else if (isPlayerStands() && isDealerStands()) {
            int result = determineWinner();
            status = switch (result) {
                case 1 -> "player-won";
                case -1 -> "dealer-won";
                default -> "tie";
            };
        } else {
            status = "in-progress";
        }
        return status;
    }

    public boolean isPlayerStands() {
        return playerStands;
    }

    public boolean isDealerStands() {
        return dealerStands;
    }

    // Convert game state to DTO for API response
    public GameStateResponse toGameStateResponse(boolean revealDealer) {
        List<String> playerCards = getPlayer().getHand().stream().map(Card::toString).toList();
        
        List<Card> hand = getDealer().getHand();
        List<String> dealerCards = IntStream.range(0, hand.size()).mapToObj(i -> {
            if (i == 0) return hand.get(i).toString(); // always show first card
            else return revealDealer ? hand.get(i).toString() : "?"; // hide others until revealDealer
        }).toList();

        return new GameStateResponse(
            playerCards,
            getPlayer().getTotalPoints(),
            dealerCards,
            revealDealer ? getDealer().getTotalPoints() : 0,
            isPlayerStands(),
            isDealerStands(),
            getStatus()
        );
    }
}