package com.company;

import java.util.Map;

public interface IPlayer {
    public Move chooseMove(Map<Character, Move> moves, Field field, Game game);

    public Cell getColor();
}
