package com.game.blackjack.dto;

import java.util.List;

public class GameStateResponse {
    private List<String> playerHand;
    private int playerPoints;

    private List<String> dealerHand;
    private int dealerPoints;

    private boolean playerStands;
    private boolean dealerStands;

    private String status; //e.g. "in-progress", "player-won", "dealer-won"

    // Constructors
    public GameStateResponse() {}

    public GameStateResponse(List<String> playerHand, int playerPoints,
                             List<String> dealerHand, int dealerPoints,
                             boolean playerStands, boolean dealerStands,
                             String status) {
        this.playerHand = playerHand;
        this.playerPoints = playerPoints;
        this.dealerHand = dealerHand;
        this.dealerPoints = dealerPoints;
        this.playerStands = playerStands;
        this.dealerStands = dealerStands;
        this.status = status;
    }

    // Getters & setters
    public List<String> getPlayerHand() { return playerHand; }
    public void setPlayerHand(List<String> playerHand) { this.playerHand = playerHand; }

    public int getPlayerPoints() { return playerPoints; }
    public void setPlayerPoints(int playerPoints) { this.playerPoints = playerPoints; }

    public List<String> getDealerHand() { return dealerHand; }
    public void setDealerHand(List<String> dealerHand) { this.dealerHand = dealerHand; }

    public int getDealerPoints() { return dealerPoints; }
    public void setDealerPoints(int dealerPoints) { this.dealerPoints = dealerPoints; }

    public boolean isPlayerStands() { return playerStands; }
    public void setPlayerStands(boolean playerStands) { this.playerStands = playerStands; }

    public boolean isDealerStands() { return dealerStands; }
    public void setDealerStands(boolean dealerStands) { this.dealerStands = dealerStands; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
