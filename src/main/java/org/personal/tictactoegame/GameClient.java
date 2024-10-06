package org.personal.tictactoegame;

import java.util.Arrays;
import java.util.List;

import org.personal.tictactoegame.controller.GameController;
import org.personal.tictactoegame.exceptions.InvalidPlayerException;
import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.personal.tictactoegame.models.Game;
import org.personal.tictactoegame.models.GameState;
import org.personal.tictactoegame.models.HumanPlayer;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.models.PlayerType;
import org.personal.tictactoegame.models.Symbol;
import org.personal.tictactoegame.winningStrategies.ColumnWinningStrategy;
import org.personal.tictactoegame.winningStrategies.DiagonalWinningStrategy;
import org.personal.tictactoegame.winningStrategies.RowWinningStrategy;
import org.personal.tictactoegame.winningStrategies.WinningStrategy;

public class GameClient {
    GameController gameController;
    RowWinningStrategy rowWinningStrategy;
    ColumnWinningStrategy columnWinningStrategy;
    DiagonalWinningStrategy diagonalWinningStrategy;

    public GameClient() {
        this.gameController = new GameController();
        this.rowWinningStrategy = new RowWinningStrategy();
        this.columnWinningStrategy = new ColumnWinningStrategy();
        this.diagonalWinningStrategy = new DiagonalWinningStrategy();
    }

    public void startGame() throws InvalidPlayerException, SymbolAlreadyExistsException {
        Player player1 = new HumanPlayer();
        player1.setPlayerType(PlayerType.HUMAN);
        player1.setName("Gaurav");
        player1.setSymbol(new Symbol("X"));

        Player player2 = new HumanPlayer();
        player2.setPlayerType(PlayerType.HUMAN);
        player2.setName("Disha");
        player2.setSymbol(new Symbol("O"));

        List<Player> players = Arrays.asList(player1, player2);
        List<WinningStrategy> winningStrategies = Arrays.asList(rowWinningStrategy, columnWinningStrategy, diagonalWinningStrategy);

        Game game = gameController.createGame(3, players, winningStrategies);
        gameController.startGame(game);
        gameController.displayBoard(game);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.makeMove(game);
            gameController.displayBoard(game);
        }

        if (game.getGameState().equals(GameState.WIN)) {
            System.out.println("Game won by " + game.getWinner().getName());
        } else if (game.getGameState().equals(GameState.DRAW)) {
            System.out.println("Bad luck no one won game, its a draw");
        }
    }
}
