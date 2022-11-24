package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Field field;

    String showPossibleMoves(Field field, List<Move> moves, Map<Character, Move> map) {
        map.clear();
        char counter = 'a';
        String separator = "+-+-+-+-+-+-+-+-+\n";
        String s = separator;
        for (int i = 0; i < field._field.length; i++) {
            String row = "|";
            for (int j = 0; j < field._field[i].length; j++) {
                int flag = 0;
                for (var element :
                        moves) {
                    if (element.toI == i && element.toJ == j) {
                        row += "" + counter + "|";
                        map.put(counter, element);
                        counter++;
                        flag++;
                        break;
                    }
                }
                if (flag == 0) {
                    row += "" + field._field[i][j] + "|";
                }
            }
            s += row + "\n" + separator;
        }
        return s;
    }

    Move evaluateMoves(List<Move> moves) {
        double maxValue = -1;
        int index = 0;
        for (int i = 0; i < moves.size(); i++) {
            Move m = moves.get(i);
            double value = m.evaluate();
            //double value = evaluate(m.fromI, m.fromJ, m.toI, m.toJ);
            if (value > maxValue) {
                maxValue = value;
                index = i;
            }
        }
        return moves.get(index);
    }

    List<Move> possibleMoves(Field field, Cell player) {
        Cell color;
        Cell oposite;
        if (player.equals(Cell.WHITE)) {
            color = Cell.WHITE;
            oposite = Cell.BLACK;
        } else {
            color = Cell.BLACK;
            oposite = Cell.WHITE;
        }
        List<Move> result = new ArrayList<Move>(64);
        for (int i = 0; i < field._field.length; i++) {
            for (int j = 0; j < field._field[i].length; j++) {
                if (field._field[i][j] == color) {
                    int c = 1;
                    int amount = 0;
                    while (j - c >= 0 && field._field[i][j - c] != Cell.EMPTY) {
                        if (field._field[i][j - c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (j - c >= 0 && field._field[i][j - c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i, j - c, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (j + c <= 7 && field._field[i][j + c] != Cell.EMPTY) {
                        if (field._field[i][j + c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (j + c <= 7 && field._field[i][j + c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i, j + c, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i + c <= 7 && field._field[i + c][j] != Cell.EMPTY) {
                        if (field._field[i + c][j] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i + c <= 7 && field._field[i + c][j] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i + c, j, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i - c >= 0 && field._field[i - c][j] != Cell.EMPTY) {
                        if (field._field[i - c][j] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i - c >= 0 && field._field[i - c][j] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i - c, j, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i - c >= 0 && j - c >= 0 && field._field[i - c][j - c] != Cell.EMPTY) {
                        if (field._field[i - c][j - c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i - c >= 0 && j - c >= 0 && field._field[i - c][j - c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i - c, j - c, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i + c <= 7 && j + c <= 7 && field._field[i + c][j + c] != Cell.EMPTY) {
                        if (field._field[i + c][j + c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i + c <= 7 && j + c <= 7 && field._field[i + c][j + c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i + c, j + c, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i + c <= 7 && j - c >= 0 && field._field[i + c][j - c] != Cell.EMPTY) {
                        if (field._field[i + c][j - c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i + c <= 7 && j - c >= 0 && field._field[i + c][j - c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i + c, j - c, i, j));
                    }
                    c = 1;
                    amount = 0;
                    while (i - c >= 0 && j + c <= 7 && field._field[i - c][j + c] != Cell.EMPTY) {
                        if (field._field[i - c][j + c] == oposite) {
                            amount++;
                        }
                        c++;
                    }
                    if (i - c >= 0 && j + c <= 7 && field._field[i - c][j + c] == Cell.EMPTY && amount != 0) {
                        result.add(new Move(i - c, j + c, i, j));
                    }
                }

            }
        }
        return result;
    }

    void makeMove(Field field, Cell player, List<Move> moves, Map<Character, Move> map) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char c = 0;
        int p = 0;
        while (true) {
            System.out.println("Enter move identificator");
            try {
                c = (char) br.read();
                p = c;
                if (p - 97 < map.size() && p - 97 >= 0) {
                    p = c;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Move move = map.get(c);
        System.out.println(move.toI + " " + move.toJ + " " + move.fromI + " " + move.fromJ);
        if (move.toI > move.fromI && move.toJ > move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ - h] = player;
            }
        } else if (move.toI < move.fromI && move.toJ < move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ + h] = player;
            }
        } else if (move.toI < move.fromI && move.toJ > move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ - h] = player;
            }
        } else if (move.toI > move.fromI && move.toJ < move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ + h] = player;
            }
        } else if (move.toI > move.fromI && move.toJ == move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ] = player;
            }
        } else if (move.toI < move.fromI && move.toJ == move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ] = player;
            }
        } else if (move.toI == move.fromI && move.toJ > move.fromJ) {
            int s = move.toJ - move.fromJ;
            for (int h = 0; h < s; h++) {
                field._field[move.toI][move.toJ - h] = player;
            }
        } else if (move.toI == move.fromI && move.toJ < move.fromJ) {
            int s = move.fromJ - move.toJ;
            for (int h = 0; h < s; h++) {
                field._field[move.toI][move.toJ + h] = player;
            }
        }
    }

    String announceWinner(Field field) {
        int black = 0;
        int white = 0;
        String result = "";
        for (int i = 0; i < field._field.length; i++) {
            for (int j = 0; j < field._field[i].length; j++) {
                if (field._field[i][j] == Cell.BLACK) {
                    black += 1;
                } else if (field._field[i][j] == Cell.WHITE) {
                    white += 1;
                }
            }
        }
        if (black > white) {
            result = "Выиграли черные со счетом: " + black + "-" + white + "\n";
        } else if (white > black) {
            result = "Выиграли белые со счетом: " + white + "-" + black + "\n";
        } else {
            result = "Ничья! Количество фишек каждой команды: " + black + "\n";
        }
        return result;
    }

    void computerMove(Field field, Cell player, Move move) {
        if (move.toI > move.fromI && move.toJ > move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ - h] = player;
            }
        } else if (move.toI < move.fromI && move.toJ < move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ + h] = player;
            }
        } else if (move.toI < move.fromI && move.toJ > move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ - h] = player;
            }
        } else if (move.toI > move.fromI && move.toJ < move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ + h] = player;
            }
        } else if (move.toI > move.fromI && move.toJ == move.fromJ) {
            int s = move.toI - move.fromI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI - h][move.toJ] = player;
            }
        } else if (move.toI < move.fromI && move.toJ == move.fromJ) {
            int s = move.fromI - move.toI;
            for (int h = 0; h < s; h++) {
                field._field[move.toI + h][move.toJ] = player;
            }
        } else if (move.toI == move.fromI && move.toJ > move.fromJ) {
            int s = move.toJ - move.fromJ;
            for (int h = 0; h < s; h++) {
                field._field[move.toI][move.toJ - h] = player;
            }
        } else if (move.toI == move.fromI && move.toJ < move.fromJ) {
            int s = move.fromJ - move.toJ;
            for (int h = 0; h < s; h++) {
                field._field[move.toI][move.toJ + h] = player;
            }
        }
    }

    public void firstScenario(List<Integer> results) {
        Field field = new Field();
        Map<Character, Move> map = new HashMap<>();
        boolean flag = true;
        List<Move> lst = possibleMoves(field, Cell.BLACK);
        while (field.isEmpty() && !lst.isEmpty()) {
            System.out.println(showPossibleMoves(field, lst, map));
            if (flag) {
                System.out.println("Ходят черные");
                makeMove(field, Cell.BLACK, lst, map);
            } else {
                System.out.println("Ходят белые");
                Move move = evaluateMoves(lst);
                computerMove(field, Cell.WHITE, move);
            }
            flag = !flag;
            System.out.println(field.printField());
            if (flag) {
                lst = possibleMoves(field, Cell.BLACK);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.WHITE);
                    flag = !flag;
                }
            } else {
                lst = possibleMoves(field, Cell.WHITE);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.BLACK);
                    flag = !flag;
                }
            }
        }
        results.add(field.countCells(Cell.BLACK));
        System.out.println("Поле заполнено или никто не может сделать ход");
        System.out.println(announceWinner(field));

    }

    public void secondScenario(List<Integer> results) {
        Field field = new Field();
        Map<Character, Move> map = new HashMap<>();
        boolean flag = true;
        List<Move> lst = possibleMoves(field, Cell.BLACK);
        while (field.isEmpty() && !lst.isEmpty()) {
            System.out.println(showPossibleMoves(field, lst, map));
            if (flag) {
                System.out.println("Ходят черные");
                Move move = evaluateMoves(lst);
                computerMove(field, Cell.BLACK, move);
            } else {
                System.out.println("Ходят белые");
                makeMove(field, Cell.WHITE, lst, map);
            }
            flag = !flag;
            System.out.println(field.printField());
            if (flag) {
                lst = possibleMoves(field, Cell.BLACK);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.WHITE);
                    flag = !flag;
                }
            } else {
                lst = possibleMoves(field, Cell.WHITE);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.BLACK);
                    flag = !flag;
                }
            }
        }
        results.add(field.countCells(Cell.WHITE));
        System.out.println("Поле заполнено или никто не может сделать ход");
        System.out.println(announceWinner(field));
    }

    public void thirdScenario(List<Integer> results) {
        Field field = new Field();
        Map<Character, Move> map = new HashMap<>();
        boolean flag = true;
        List<Move> lst = possibleMoves(field, Cell.BLACK);
        while (field.isEmpty() && !lst.isEmpty()) {
            System.out.println(showPossibleMoves(field, lst, map));
            if (flag) {
                System.out.println("Ходят черные");
                makeMove(field, Cell.BLACK, lst, map);
            } else {
                System.out.println("Ходят белые");
                makeMove(field, Cell.WHITE, lst, map);
            }
            flag = !flag;
            System.out.println(field.printField());
            if (flag) {
                lst = possibleMoves(field, Cell.BLACK);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.WHITE);
                    flag = !flag;
                }
            } else {
                lst = possibleMoves(field, Cell.WHITE);
                if (lst.isEmpty()) {
                    lst = possibleMoves(field, Cell.BLACK);
                    flag = !flag;
                }
            }
        }
        results.add(field.countCells(Cell.BLACK));
        results.add(field.countCells(Cell.WHITE));
        System.out.println("Поле заполнено или никто не может сделать ход");
        System.out.println(announceWinner(field));
    }
}
