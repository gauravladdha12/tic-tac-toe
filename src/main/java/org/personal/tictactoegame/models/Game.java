package org.personal.tictactoegame.models;

import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameState gameState;
    Player winner;
}