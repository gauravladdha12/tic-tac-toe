package org.personal.tictactoegame.winningStrategies;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Player;

public interface WinningStrategy {
    boolean isPlayerWon(Board board, Player player);
}
