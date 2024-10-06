package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Move {
    Player player;
    Cell cell;
    Symbol symbol;
}
