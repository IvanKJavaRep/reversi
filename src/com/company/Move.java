package com.company;

class Move {
    int toI, toJ;
    int fromI, fromJ;
    double value;

    Move(int i, int j, int fI, int fJ) {
        this.toI = i;
        this.toJ = j;
        fromI = fI;
        fromJ = fJ;
    }

    @Override
    public String toString() {
        return "Move{" +
                "i=" + toI +
                ", j=" + toJ +
                ", fromI=" + fromI +
                ", fromJ=" + fromJ +
                '}';
    }

    double evaluate() {
        double summ = 0;
        if (fromI == toI && (toI == 0 || toI == 7)) {
            if ((toI == 7 && toJ == 7) || (toI == 0 && toJ == 0) || (toI == 0 && toJ == 7) || (toI == 7 && toJ == 0)) {
                summ = 2 * (Math.abs(fromJ - toJ) - 1) + 0.8;
            } else {
                summ = 2 * (Math.abs(fromJ - toJ) - 1) + 0.4;
            }
        } else if (fromJ == toJ && (toJ == 0 || toJ == 7)) {
            if ((toI == 7 && toJ == 7) || (toI == 0 && toJ == 0) || (toI == 0 && toJ == 7) || (toI == 7 && toJ == 0)) {
                summ = 2 * (Math.abs(fromI - toI) - 1) + 0.8;
            } else {
                summ = 2 * (Math.abs(fromI - toI) - 1) + 0.4;
            }
        } else if (fromI == toI && (toI != 0 || toI != 7)) {
            if (toI == 0 || toI == 7) {
                summ = Math.abs(toJ - fromJ) - 1 + 0.4;
            } else {
                summ = Math.abs(toJ - fromJ) - 1;
            }
        } else if (fromJ == toJ && (toJ != 0 || toJ != 7)) {
            if (toJ == 0 || toJ == 7) {
                summ = Math.abs(toI - fromI) - 1 + 0.4;
            } else {
                summ = Math.abs(toI - fromI) - 1;
            }
        } else if (Math.abs(toI - fromI) == Math.abs(toJ - fromJ)) {
            if ((toI == 0 && toJ == 0) || (toI == 7 && toJ == 7) || (toI == 0 && toJ == 7) || (toI == 7 && toJ == 0)) {
                summ = Math.abs(toI - fromI) - 1 + 0.8;
            } else if (toI == 0 || toI == 7 || toJ == 0 || toJ == 7) {
                summ = Math.abs(toI - fromI) - 1 + 0.4;
            } else {
                summ = Math.abs(toI - fromI) - 1;
            }
        }
        return summ;
    }
}
