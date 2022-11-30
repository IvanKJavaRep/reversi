package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PersonPlayer implements IPlayer {
    private Cell color;

    public Cell getColor() {
        return color;
    }

    public PersonPlayer(Cell color) {
        this.color = color;
    }

    public static PersonPlayer getEnemy(ComputerPlayer computerPlayer) {
        if (computerPlayer.getColor() == Cell.BLACK) {
            return new PersonPlayer(Cell.WHITE);
        }
        return new PersonPlayer(Cell.BLACK);
    }

    public Move chooseMove(Map<Character, Move> moves, Field field, Game game) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character input = 'A';
        System.out.println("Введите один из символов, предложенных на поле");
        while (true) {
            try {
                input = br.readLine().charAt(0);
                if (moves.containsKey(input)) {
                    return moves.get(input);
                } else {
                    System.out.println("Введите правильный симовл");
                }
            } catch (Exception e) {
                System.out.println("Введите еще раз");
            }
        }
    }
}
