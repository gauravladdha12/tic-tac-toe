package org.personal.tictactoegame.undoStrategies;

import java.util.Arrays;
import java.util.List;

import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.personal.tictactoegame.models.Game;
import org.personal.tictactoegame.models.Move;
import org.personal.tictactoegame.service.MoveManager;
import org.personal.tictactoegame.winningStrategies.ColumnWinningStrategy;
import org.personal.tictactoegame.winningStrategies.DiagonalWinningStrategy;
import org.personal.tictactoegame.winningStrategies.O1WinningStrategy;
import org.personal.tictactoegame.winningStrategies.RowWinningStrategy;

public class RedoUndoStrategy implements UndoStrategy {

    MoveManager moveManager = new MoveManager();
    RowWinningStrategy rowWinningStrategy = new RowWinningStrategy();
    ColumnWinningStrategy columnWinningStrategy = new ColumnWinningStrategy();
    DiagonalWinningStrategy diagonalWinningStrategy = new DiagonalWinningStrategy();

    List<O1WinningStrategy> o1WinningStrategies = Arrays.asList(rowWinningStrategy, columnWinningStrategy, diagonalWinningStrategy);

    @Override
    public Game undo(Game game) throws SymbolAlreadyExistsException {
        List<Move> moves = game.getMoves();
        Game newGame = game.emptyCellsClone();

        for (O1WinningStrategy o1WinningStrategy : o1WinningStrategies) {
            o1WinningStrategy.resetSymbolCountMap();
        }

        for (int i = 0; i < moves.size() - 1; i++) {
            moveManager.makeMove(newGame, moves.get(i));
        }

        if (moves.size() == 1) {
            newGame.setCurrentPlayerIndex(null);
        } else {
            newGame.setAndGetPreviousPlayer();
        }
        return newGame;
    }
}
