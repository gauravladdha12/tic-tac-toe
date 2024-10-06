package org.personal.tictactoegame.controller;

import java.util.List;

import org.personal.tictactoegame.exceptions.InvalidPlayerException;
import org.personal.tictactoegame.models.Game;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.service.GameService;
import org.personal.tictactoegame.winningStrategies.WinningStrategy;

public class GameController {

    private GameService gameService;

    public GameController() {
        this.gameService = new GameService();
    }

    public void displayBoard(Game game) {
        gameService.displayBoard(game);
    }

    public Game createGame(int boardSize, List<Player> players, List<WinningStrategy> winningStrategies) throws InvalidPlayerException {
        if (players.size() < 2) {
            throw new InvalidPlayerException("Minimum number of players should be 2 to start game");
        }
        return gameService.createGame(boardSize, players, winningStrategies);
    }

    public void makeMove(Game game) {
        gameService.makeMove(game);
    }
}
