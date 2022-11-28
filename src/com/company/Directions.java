package com.company;

public enum Directions {
    W(0, -1),
    N(-1, 0),
    S(1, 0),
    E(0, 1),
    NW(-1, -1),
    NE(-1, 1),
    SE(1, 1),
    SW(1, -1);
    int di, dj;

    Directions(int i, int j) {
        this.di = i;
        this.dj = j;
    }
}
