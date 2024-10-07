package org.personal.tictactoegame.winningStrategies;

import java.util.HashMap;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.models.Symbol;

public class RowWinningStrategy implements O1WinningStrategy {
    static HashMap<Integer, HashMap<Symbol, Integer>> symbolCountMap = new HashMap<>();

    @Override
    public boolean isPlayerWon(final Board board, final Player player, final Cell cell) {
        int row = cell.getRow();
        HashMap<Symbol, Integer> rowSymbolMap = symbolCountMap.getOrDefault(row, new HashMap<>());
        rowSymbolMap.put(cell.getSymbol(), rowSymbolMap.getOrDefault(cell.getSymbol(), 0) + 1);
        symbolCountMap.put(row, rowSymbolMap);

        return symbolCountMap.get(row).get(cell.getSymbol()) == board.getSize();
    }

    public void resetSymbolCountMap() {
        symbolCountMap = new HashMap<>();
    }
}
