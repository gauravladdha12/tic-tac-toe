package org.personal.tictactoegame.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Board {
    int size;
    List<List<Cell>> cells;

    private Board() {

    }

    public static class builder {
        int size;

        public builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Board build() {
            Board board = new Board();
            board.setSize(size);
            List<List<Cell>> emptyCells = getEmptyCells(size);
            board.setCells(emptyCells);
            return board;
        }

        private List<List<Cell>> getEmptyCells(int size) {
            List<List<Cell>> cells = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<Cell> columnCells = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    Cell cell = Cell.builder()
                            .row(i).column(j).build();
                    columnCells.add(cell);
                }
                cells.add(columnCells);
            }
            return cells;
        }

    }
}
