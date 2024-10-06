package org.personal.tictactoegame.botPlayStrategies;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Move;
import org.personal.tictactoegame.models.Player;

public interface BotMoveStrategy {
    Move makeMove(Board board, Player player);
}
