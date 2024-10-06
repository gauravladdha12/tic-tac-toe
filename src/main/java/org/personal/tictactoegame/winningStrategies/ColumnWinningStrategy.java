package org.personal.tictactoegame.winningStrategies;

import java.util.HashMap;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.models.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {
    HashMap<Integer, HashMap<Symbol, Integer>> symbolCountMap = new HashMap<>();

    @Override
    public boolean isPlayerWon(final Board board, final Player player, final Cell cell) {
        int column = cell.getColumn();
        HashMap<Symbol, Integer> rowSymbolMap = symbolCountMap.getOrDefault(column, new HashMap<>());
        rowSymbolMap.put(cell.getSymbol(), rowSymbolMap.getOrDefault(cell.getSymbol(), 0) + 1);
        symbolCountMap.put(column, rowSymbolMap);

        return symbolCountMap.get(column).get(cell.getSymbol()) == board.getSize();
    }
}
