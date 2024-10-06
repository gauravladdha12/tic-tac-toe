package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public abstract class Player {
    String name;
    Symbol symbol;
    PlayerType playerType;

    public abstract Move getNextMove(Board board);
}
