package org.personal.tictactoegame.models;

import java.util.ArrayList;
import java.util.List;

import org.personal.tictactoegame.winningStrategies.WinningStrategy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    List<WinningStrategy> winningStrategies;
    GameState gameState;
    Player winner;
    Integer currentPlayerIndex;

    public Player setAndGetNextPlayer() {
        if (currentPlayerIndex == null) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = currentPlayerIndex + 1;
            currentPlayerIndex = currentPlayerIndex % players.size();
        }
        return players.get(currentPlayerIndex);
    }

    public Player setAndGetPreviousPlayer() {
        if (currentPlayerIndex == null) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = currentPlayerIndex - 1;
            currentPlayerIndex = (currentPlayerIndex % players.size() + players.size()) % players.size();
        }
        return players.get(currentPlayerIndex);
    }

    public Game emptyCellsClone() {
        Board emptyBoard = new Board.builder()
                .setSize(this.board.getSize())
                .build();
        return Game.builder()
                .board(emptyBoard)
                .players(this.players)
                .moves(new ArrayList<>())
                .winningStrategies(this.winningStrategies)
                .gameState(GameState.IN_PROGRESS)
                .currentPlayerIndex(this.currentPlayerIndex)
                .build();
    }

    public void updateFrom(final Game undo) {
        this.board = undo.board;
        this.players = undo.players;
        this.moves = undo.moves;
        this.winningStrategies = undo.winningStrategies;
        this.gameState = undo.gameState;
        this.winner = undo.winner;
        this.currentPlayerIndex = undo.currentPlayerIndex;
    }
}