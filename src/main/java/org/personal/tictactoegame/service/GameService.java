package org.personal.tictactoegame.service;

import java.util.ArrayList;
import java.util.List;

import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Game;
import org.personal.tictactoegame.models.GameState;
import org.personal.tictactoegame.models.Move;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.undoStrategies.RedoUndoStrategy;
import org.personal.tictactoegame.undoStrategies.UndoStrategy;
import org.personal.tictactoegame.winningStrategies.WinningStrategy;

public class GameService {

    MoveManager moveManager;
    UndoStrategy undoStrategy;

    public GameService() {
        moveManager = new MoveManager();
        undoStrategy = new RedoUndoStrategy();
    }

    public void displayBoard(final Game game) {
        Board board = game.getBoard();
        List<List<Cell>> cells = board.getCells();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                Cell cell = cells.get(i).get(j);
                if (cell.getSymbol() == null) {
                    System.out.print("|   |");
                } else {
                    System.out.print("| " + cell.getSymbol().getValue() + " |");
                }
            }
            System.out.println();
        }
    }

    public Game createGame(final int boardSize, final List<Player> players, final List<WinningStrategy> winningStrategies) {
        Board board = new Board.builder()
                .setSize(boardSize)
                .build();

        return Game.builder()
                .gameState(GameState.NOT_STARTED)
                .board(board)
                .winningStrategies(winningStrategies)
                .players(players)
                .moves(new ArrayList<>())
                .build();
    }

    public void makeMove(final Game game) throws SymbolAlreadyExistsException {
        if (!game.getGameState().equals(GameState.IN_PROGRESS)) {
            throw new IllegalStateException("Game is not in progress state, current state: " + game.getGameState());
        }

        Board board = game.getBoard();
        Player currentPlayer = game.setAndGetNextPlayer();
        Move move = currentPlayer.getNextMove(board);

        try {
            moveManager.makeMove(game, move);
        } catch (SymbolAlreadyExistsException e) {
            // undo current player
            game.setAndGetPreviousPlayer();
            throw e;
        }
    }

    public void undo(final Game game) throws SymbolAlreadyExistsException {
        Game undo = undoStrategy.undo(game);
        game.updateFrom(undo);
    }
}