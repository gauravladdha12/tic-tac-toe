package org.personal.tictactoegame.factories;

import org.personal.tictactoegame.botPlayStrategies.BotMoveStrategy;
import org.personal.tictactoegame.botPlayStrategies.FirstEmptyMoveStrategy;
import org.personal.tictactoegame.models.DifficultyLevel;

public class BotPlayStrategyFactory {
    public static BotMoveStrategy getBotMoveStrategy(DifficultyLevel difficultyLevel) {
        return new FirstEmptyMoveStrategy();
    }
}
