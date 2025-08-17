package com.game.blackjack.controller;

import com.game.blackjack.dto.GameStateResponse;
import com.game.blackjack.service.BlackjackService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/blackjack")
public class BlackjackController {
    
    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    @PostMapping("/start")
    public GameStateResponse startGame() {
        blackjackService.startGame();
        return blackjackService.getGame().toGameStateResponse(false);
    }

    @PostMapping("/player/hit")
    public GameStateResponse playerHit() {
        blackjackService.playerHit();
        return blackjackService.getGame().toGameStateResponse(false);
    }

    @PostMapping("/player/stand")
    public GameStateResponse playerStand() {
        blackjackService.playerStand();
        return blackjackService.getGame().toGameStateResponse(false);
    }

    @PostMapping("/dealer/next")
    public GameStateResponse dealerDrawNext() {
        blackjackService.dealerDrawNextCard();
        boolean revealDealer = blackjackService.getGame().isDealerStands();
        return blackjackService.getGame().toGameStateResponse(revealDealer);
    }
    

    @GetMapping("/state")
    public GameStateResponse getState() {
        blackjackService.getGame();
        return blackjackService.getGame().toGameStateResponse(false);
    }
}
