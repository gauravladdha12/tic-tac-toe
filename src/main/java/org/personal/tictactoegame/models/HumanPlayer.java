package org.personal.tictactoegame.models;

import java.util.Scanner;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class HumanPlayer extends Player {

    @Override
    public Move getNextMove(Board board) {
        // get input from a client
        Scanner sc = new Scanner(System.in);
        System.out.println("Turn for player: " + this.getName());
        System.out.println("Enter row number starting 1");
        int row = Integer.parseInt(sc.nextLine()) - 1;
        System.out.println("Enter column number starting 1");
        int column = Integer.parseInt(sc.nextLine()) - 1;
        Cell cell = new Cell(row, column, this.getSymbol());
        return new Move(this, cell);
    }
}
