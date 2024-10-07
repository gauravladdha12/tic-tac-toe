package org.personal.tictactoegame.undoStrategies;

import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.personal.tictactoegame.models.Game;

public interface UndoStrategy {

    Game undo(Game game) throws SymbolAlreadyExistsException;
}
