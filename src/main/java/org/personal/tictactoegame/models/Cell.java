package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cell {
    int row;
    int column;
    Symbol symbol;
}
