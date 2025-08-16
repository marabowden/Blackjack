import java.util.*;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private int pot;

    public Game() {
        deck = new Deck();
        player = new Player("Player");
        dealer = new Dealer();
        int pot = 0;
    }

    public void startGame() {
        deck.shuffle();
        dealInitialCards();
        System.out.println("Game started!");
        deck.printDeck();
        gameLoop();
    }

    private void dealInitialCards() {
        player.addACard(deck.drawCard());
        dealer.addACard(deck.drawCard());
        player.addACard(deck.drawCard());
        dealer.addACard(deck.drawCard());
    }

    public boolean isGameOver() {
        return player.isBusted() || dealer.isBusted();
    }

    // Add more game logic methods as needed
    private void gameLoop() {
        dealer.showFirstCard();
        boolean playerStands = false;
        boolean dealerStands = false;
        if (!isGameOver()) {
            // Player's turn logic here (hit, stand, etc.)
            while (!playerStands) {
                System.out.println("Player's turn. Current points: " + player.getTotalPoints());
                boolean playerDraw = player.playLogic();
                if (playerDraw) {
                    player.addACard(deck.drawCard());
                    System.out.println("Player draws a card. Current points: " + player.getTotalPoints());
                } else {
                    playerStands = true;
                    System.out.println("Player stands with " + player.getTotalPoints() + " points.");
                }
            }

            // Dealer's turn logic here (hit, stand, etc.)
            dealer.showSecondCard();
            while (!dealerStands && !isGameOver()) {
                System.out.println("Dealer's turn. Current points: " + dealer.getTotalPoints());
                boolean dealerDraw = dealer.playLogic();
                if (dealerDraw) {
                    dealer.addACard(deck.drawCard());
                    System.out.println("Dealer draws a card. Current points: " + dealer.getTotalPoints());
                } else {
                    dealerStands = true;
                    System.out.println("Dealer stands with " + dealer.getTotalPoints() + " points.");
                }
            }
        }

        // Determine winner
        determineWinner();
    }

    private int determineWinner() { // Returns 0 for tie, 1 for player win, -1 for dealer win
        int playerPoints = player.getTotalPoints();
        int dealerPoints = dealer.getTotalPoints();

        if (player.isBusted()) {
            System.out.println("Player busted! Dealer wins.");
            return -1;
        } else if (dealer.isBusted()) {
            System.out.println("Dealer busted! Player wins.");
            return 1;
        } else if (player.hasBlackjack() && dealer.hasBlackjack()) {
            System.out.println("Both have blackjack! It's a tie.");
            return 0;
        } else if (player.hasBlackjack()) {
            System.out.println("Player has blackjack! Player wins!");
            return 1;
        } else if (dealer.hasBlackjack()) {
            System.out.println("Dealer has blackjack! Dealer wins!");
            return -1;
        } else if (playerPoints > dealerPoints) {
            System.out.println("Player wins with " + playerPoints + " points!");
            return 1;
        } else if (dealerPoints > playerPoints) {
            System.out.println("Dealer wins with " + dealerPoints + " points!");
            return -1;
        } else {
            System.out.println("It's a tie with both having " + playerPoints + " points!");
            return 0;
        }
    }
}