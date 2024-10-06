package org.personal.tictactoegame;

import org.personal.tictactoegame.exceptions.InvalidPlayerException;
import org.personal.tictactoegame.exceptions.SymbolAlreadyExistsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeGameApplication {

    public static void main(String[] args) throws InvalidPlayerException, SymbolAlreadyExistsException {
        SpringApplication.run(TicTacToeGameApplication.class, args);
        GameClient gameClient = new GameClient();
        gameClient.startGame();
    }

}
