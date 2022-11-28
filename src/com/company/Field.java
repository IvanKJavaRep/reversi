package com.company;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private Cell[][] _field;

    Field() {
        set_field(new Cell[8][8]);
        for (int i = 0; i < get_field().length; i++)
            for (int j = 0; j < get_field()[i].length; j++)
                get_field()[i][j] = Cell.EMPTY;
        get_field()[3][3] = Cell.WHITE;
        get_field()[3][4] = Cell.BLACK;
        get_field()[4][3] = Cell.BLACK;
        get_field()[4][4] = Cell.WHITE;
    }


    String printField(Map<Character, Move> map) {
        String separator = "+-+-+-+-+-+-+-+-+\n";
        String s = separator;
        Map<Move, Character> inversal = new HashMap<>();
        for (var el :
                map.keySet()) {
            inversal.put(map.get(el), el);
        }
        for (int i = 0; i < get_field().length; i++) {
            String row = "|";
            for (int j = 0; j < get_field()[i].length; j++) {
                if (inversal.containsKey(new Move(i,j))) {
                    row += "" + inversal.get(new Move(i, j)).charValue() + "|";
                } else {
                    row += "" + get_field()[i][j] + "|";
                }
            }
            s += row + "\n" + separator;
        }
        return s;
    }

    public Cell[][] get_field() {
        return _field;
    }

    public void set_field(Cell[][] _field) {
        this._field = _field;
    }
}
