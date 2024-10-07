package org.personal.tictactoegame.winningStrategies;

import java.util.HashMap;

import org.personal.tictactoegame.models.Board;
import org.personal.tictactoegame.models.Cell;
import org.personal.tictactoegame.models.Player;
import org.personal.tictactoegame.models.Symbol;

public class DiagonalWinningStrategy implements O1WinningStrategy {
    static HashMap<Integer, HashMap<Symbol, Integer>> symbolCountMap = new HashMap<>();

    @Override
    public boolean isPlayerWon(final Board board, final Player player, final Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int identifier;
        if (row == column) {
            identifier = 0;
        } else if (row + column == board.getSize()) {
            identifier = 1;
        } else {
            return false;
        }
        HashMap<Symbol, Integer> rowSymbolMap = symbolCountMap.getOrDefault(identifier, new HashMap<>());
        rowSymbolMap.put(cell.getSymbol(), rowSymbolMap.getOrDefault(cell.getSymbol(), 0) + 1);
        symbolCountMap.put(identifier, rowSymbolMap);

        return symbolCountMap.get(identifier).get(cell.getSymbol()) == board.getSize();
    }

    public void resetSymbolCountMap() {
        symbolCountMap = new HashMap<>();
    }
}
