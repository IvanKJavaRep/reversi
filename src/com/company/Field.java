package com.company;

public class Field {
    Cell[][] _field;

    Field() {
        _field = new Cell[8][8];
        for (int i = 0; i < _field.length; i++)
            for (int j = 0; j < _field[i].length; j++)
                _field[i][j] = Cell.EMPTY;
        _field[3][3] = Cell.WHITE;
        _field[3][4] = Cell.BLACK;
        _field[4][3] = Cell.BLACK;
        _field[4][4] = Cell.WHITE;
    }

    boolean isEmpty() {
        int c = 0;
        for (int i = 0; i < _field.length; i++) {
            for (int j = 0; j < _field[i].length; j++) {
                if (_field[i][j] == Cell.EMPTY) {
                    c++;
                }
            }
        }
        if (c == 0) {
            return false;
        }
        return true;
    }

    int countCells(Cell color) {
        int amount = 0;
        for (int i = 0; i < _field.length; i++) {
            for (int j = 0; j < _field[i].length; j++) {
                if (_field[i][j] == color) {
                    amount++;
                }
            }
        }
        return amount;
    }

    String printField() {
        String separator = "+-+-+-+-+-+-+-+-+\n";
        String s = separator;
        for (int i = 0; i < _field.length; i++) {
            String row = "|";
            for (int j = 0; j < _field[i].length; j++) {
                row += "" + _field[i][j] + "|";
            }
            s += row + "\n" + separator;
        }
        return s;
    }
}
