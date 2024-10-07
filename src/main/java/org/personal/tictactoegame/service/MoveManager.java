package org.personal.tictactoegame.service;

import java.util.List;

import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Game;
import org.personal.tictactoegame.models.GameState;
import org.personal.tictactoegame.models.Move;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.winningStrategies.WinningStrategy;

public class MoveManager {
    public void makeMove(Game game, Move move) throws SymbolAlreadyExistsException {
        game.getMoves().add(move);
        List<List<Cell>> cells = game.getBoard().getCells();
        Cell moveCell = move.getCell();
        Cell cell = cells.get(moveCell.getRow()).get(moveCell.getColumn());
        if (cell.getSymbol() != null) {
            throw new SymbolAlreadyExistsException("Given place already have a symbol " + cell.getSymbol().getValue());
        }
        cell.setSymbol(moveCell.getSymbol());

        changeGameState(game, move.getPlayer(), cell);
    }

    public void changeGameState(final Game game, final Player player, final Cell cell) {
        List<WinningStrategy> winningStrategies = game.getWinningStrategies();
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.isPlayerWon(game.getBoard(), player, cell)) {
                game.setGameState(GameState.WIN);
                game.setWinner(player);
                break;
            }
        }

        int boardSize = game.getBoard().getSize();
        if (game.getMoves().size() == boardSize * boardSize) {
            game.setGameState(GameState.DRAW);
        }
    }
}
