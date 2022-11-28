package com.company;

import java.util.List;
import java.util.Map;

public class ComputerPlayer implements IPlayer {
    private Cell color;

    public Cell getColor() {
        return color;
    }

    public ComputerPlayer(Cell player) {
        this.color = player;
    }


    public Move chooseMove(Map<Character, Move> moves, Field field, Game game) {
        return criterion(field, game.possible_moves(field, this.getColor()), game);
    }

    Move criterion(Field field, List<Move> moves, Game game) {
        double m = -1;
        Move max_move = null;
        for (var el :
                moves) {
            double s = current_cell_kind(el.getToI(), el.getToJ());
            for (var move : game.closing(field, el.getToI(), el.getToJ(), color)) {
                s += closing_cell_kind(move.getToI(), move.getToJ());
            }
            if (s > m) {
                m = s;
                max_move = new Move(el.getToI(), el.getToJ());
            }
        }
        return max_move;
    }

    int closing_cell_kind(int i, int j) {
        if (i == 0 || j == 0 || i == 7 || j == 7) {
            return 2;
        }
        return 1;
    }

    double current_cell_kind(int i, int j) {
        if ((i == 0 && j == 0) || (i == 7 && j == 7)) {
            return 0.8;
        }
        if (i == 0 || j == 0 || i == 7 || j == 7) {
            return 0.4;
        }
        return 0;
    }
}
