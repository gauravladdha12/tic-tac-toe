package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Player {
    String name;
    Symbol symbol;
    PlayerType playerType;
}
