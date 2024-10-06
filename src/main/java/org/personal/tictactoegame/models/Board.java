package org.personal.tictactoegame.models;

import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Board {
    int size;
    List<List<Cell>> cells;
}
