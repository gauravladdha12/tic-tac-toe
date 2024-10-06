package org.personal.tictactoegame.models;

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

    public Player getNextPlayer() {
        if (currentPlayerIndex == null) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = currentPlayerIndex + 1;
            currentPlayerIndex = currentPlayerIndex % players.size();
        }
        return players.get(currentPlayerIndex);
    }

    public Player getPreviousPlayer() {
        if (currentPlayerIndex == null) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = currentPlayerIndex - 1;
            currentPlayerIndex = (currentPlayerIndex % players.size() + players.size()) % players.size();
        }
        return players.get(currentPlayerIndex);
    }
}