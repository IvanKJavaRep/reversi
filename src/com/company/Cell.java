package com.company;

enum Cell {
    EMPTY, BLACK, WHITE;

    @Override
    public String toString() {
        switch (this) {
            case BLACK -> {
                return "x";
            }
            case WHITE -> {
                return "o";
            }
            case EMPTY -> {
                return " ";
            }
        }
        return "ERROR";
    }
}
