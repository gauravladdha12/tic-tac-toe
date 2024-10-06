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
import org.personal.tictactoegame.winningStrategies.WinningStrategy;

public class GameService {

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
        List<List<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            List<Cell> columnCells = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                Cell cell = Cell.builder()
                        .row(i).column(j).build();
                columnCells.add(cell);
            }
            cells.add(columnCells);
        }

        Board board = Board.builder()
                .cells(cells)
                .size(boardSize).build();

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
        Player currentPlayer = game.getNextPlayer();

        Move move = currentPlayer.getNextMove(board);
        game.getMoves().add(move);

        List<List<Cell>> cells = board.getCells();
        Cell moveCell = move.getCell();
        Cell cell = cells.get(moveCell.getRow()).get(moveCell.getColumn());
        if (cell.getSymbol() != null) {
            // undo current player
            game.getPreviousPlayer();
            throw new SymbolAlreadyExistsException("Given place already have a symbol " + cell.getSymbol().getValue());
        }
        cell.setSymbol(moveCell.getSymbol());

        changeGameState(game, currentPlayer, cell);
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
