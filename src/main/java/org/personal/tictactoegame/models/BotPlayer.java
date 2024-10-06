package org.personal.tictactoegame.models;

import org.personal.tictactoegame.factories.BotPlayStrategyFactory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BotPlayer extends Player {

    DifficultyLevel difficultyLevel;

    @Override
    public Move getNextMove(Board board) {
        System.out.println("Bot making move");
        return BotPlayStrategyFactory.getBotMoveStrategy(this.difficultyLevel).makeMove(board, this);
    }
}
