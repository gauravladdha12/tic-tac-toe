package org.personal.tictactoegame.winningStrategies;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Player;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean isPlayerWon(final Board board, final Player player) {
        return false;
    }
}
