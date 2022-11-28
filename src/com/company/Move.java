package com.company;

import java.util.Objects;

class Move {
    private int toI;
    private int toJ;

    Move(int i, int j) {
        this.setToI(i);
        this.setToJ(j);
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + getToI() +
                ", j=" + getToJ() +
                ", fromI=" +
                ", fromJ=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return getToI() == move.getToI() && getToJ() == move.getToJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToI(), getToJ());
    }

    public int getToI() {
        return toI;
    }

    public void setToI(int toI) {
        this.toI = toI;
    }

    public int getToJ() {
        return toJ;
    }

    public void setToJ(int toJ) {
        this.toJ = toJ;
    }
}
