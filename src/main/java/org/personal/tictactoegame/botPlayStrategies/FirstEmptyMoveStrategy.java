package org.personal.tictactoegame.botPlayStrategies;

import java.util.List;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Move;
import org.personal.tictactoegame.models.Player;

public class FirstEmptyMoveStrategy implements EasyMoveStrategy {
    @Override
    public Move makeMove(final Board board, final Player player) {
        List<List<Cell>> cells = board.getCells();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                Cell cell = cells.get(i).get(j);
                if (cell.getSymbol() == null) {
                    Cell moveCell = Cell.builder()
                            .row(cell.getRow())
                            .column(cell.getColumn())
                            .symbol(player.getSymbol()).build();
                    return Move.builder()
                            .player(player)
                            .cell(moveCell).build();
                }
            }
        }
        return null;
    }
}
