package org.personal.tictactoegame.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Symbol {
    String value;

    public Symbol(final String value) {
        this.value = value;
    }
}
