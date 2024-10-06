package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Cell {
    int row;
    int column;
    Symbol symbol;
}
