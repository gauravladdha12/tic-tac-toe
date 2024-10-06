package org.personal.tictactoegame.models;

import java.util.List;

import org.personal.tictactoegame.winningStrategies.WinningStrategy;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    List<WinningStrategy> winningStrategies;
    GameState gameState;
    Player winner;
}