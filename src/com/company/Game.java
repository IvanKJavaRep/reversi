package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    private List<Integer> results = new ArrayList<>();

    Map<Character, Move> makeMoves(Field field, Cell player) {
        Map<Character, Move> map = new HashMap<>();
        String letters = "ABCDEFGHIJKLMNPQRSTUVWYZ";
        int i = 0;
        for (var move :
                possible_moves(field, player)) {
            map.put(letters.charAt(i), move);
            i++;
        }
        return map;
    }

    public Character chooseGameType() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character input = '1';
        while (true) {
            try {
                input = br.readLine().charAt(0);
                if (input == '1' || input == '2' || input == '3' || input == 'x') {
                    return input;
                } else {
                    System.out.println("Введите правильный симовл");
                }
            } catch (Exception e) {
                System.out.println("Введите еще раз");
            }
        }
    }

    public ComputerPlayer chooseComputerColor() {
        System.out.println("Вы выбрали режим игры с искусственным интеллектом\n" +
                "Если хотите, чтобы компьютер играл за черных, введите 1, иначе - 2\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Character input = '1';
        while (true) {
            try {
                String in = br.readLine();
                if (in != null) {
                    input = in.charAt(0);
                    if (input == '1') {
                        return new ComputerPlayer(Cell.BLACK);
                    } else if (input == '2') {
                        return new ComputerPlayer(Cell.WHITE);
                    } else {
                        System.out.println("Введите правильный симовл");
                    }
                } else {
                    System.out.println("Введите правильный симовл");
                }
            } catch (Exception e) {
                System.out.println("Введите еще раз");
            }
        }
    }


    boolean in_field(int i, int j) {
        return (0 <= i && i <= 7) && (0 <= j && j <= 7);
    }


    boolean can_move(int i, int j, Cell player, Field field) {
        Cell enemy;
        if (player == Cell.WHITE) {
            enemy = Cell.BLACK;
        } else {
            enemy = Cell.WHITE;
        }
        return (in_field(i, j) && (field.get_field()[i][j] == Cell.EMPTY) &&
                neighbours(field, i, j).contains(enemy) &&
                !closing(field, i, j, player).isEmpty());
    }

    List<Cell> neighbours(Field field, int i, int j) {
        List<Cell> neighbours = new ArrayList<>();
        for (var direction :
                Directions.values()) {
            if (in_field(i + direction.di, j + direction.dj)) {
                neighbours.add(field.get_field()[i + direction.di][j + direction.dj]);
            }
        }
        return neighbours;
    }


    List<Move> closing(Field field, int o_i, int o_j, Cell player) {
        List<Move> result = new ArrayList<>();
        for (var direction : Directions.values()) {
            List<Move> res1 = new ArrayList<>();
            int i = o_i + direction.di;
            int j = o_j + direction.dj;
            while (!((!in_field(i, j)) || field.get_field()[i][j] == player || field.get_field()[i][j] == Cell.EMPTY)) {
                res1.add(new Move(i, j));
                i += direction.di;
                j += direction.dj;
            }
            if (in_field(i, j) && field.get_field()[i][j] == player) {
                for (var move :
                        res1) {
                    result.add(move);
                }
            }
        }
        return result;
    }


    List<Move> possible_moves(Field field, Cell player) {
        List<Move> pos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (can_move(i, j, player, field)) {
                    pos.add(new Move(i, j));
                }
            }
        }
        return pos;
    }

    void make_move(Field field, int i, int j, Cell player) {
        for (var point : closing(field, i, j, player)) {
            field.get_field()[point.getToI()][point.getToJ()] = player;
            field.get_field()[i][j] = player;
        }
    }

    String announceWinner(Field field) {
        int black = 0;
        int white = 0;
        String result = "";
        for (int i = 0; i < field.get_field().length; i++) {
            for (int j = 0; j < field.get_field()[i].length; j++) {
                if (field.get_field()[i][j] == Cell.BLACK) {
                    black += 1;
                } else if (field.get_field()[i][j] == Cell.WHITE) {
                    white += 1;
                }
            }
        }
        if (black > white) {
            result = "Выиграли черные со счетом: " + black + "-" + white + "\n";
            results.add(black);
        } else if (white > black) {
            result = "Выиграли белые со счетом: " + white + "-" + black + "\n";
            results.add(white);
        } else {
            result = "Ничья! Количество фишек каждой команды: " + black + "\n";
            results.add(black);
        }
        return result;
    }

    public void playerVsPlayer(IPlayer blackPlayer, IPlayer whiteplayer, Field field) {
        boolean no_white_moves = false;
        boolean no_black_moves = false;
        IPlayer player = blackPlayer;
        Move move;
        while (!(no_white_moves && no_black_moves)) {
            var moves = makeMoves(field, player.getColor());
            System.out.println(field.printField(moves));
            if (player.getColor() == Cell.BLACK) {
                if (moves.isEmpty()) {
                    no_black_moves = true;
                    System.out.println("Нет черных ходов");
                    player = whiteplayer;
                    continue;
                }
                no_white_moves = false;
                System.out.println("Ходят черные");
                move = player.chooseMove(moves, field, this);
            } else {
                if (moves.isEmpty()) {
                    no_white_moves = true;
                    System.out.println("Нет белых ходов");
                    player = blackPlayer;
                    continue;
                }
                no_white_moves = false;
                System.out.println("Ходят белые");
                move = player.chooseMove(moves, field, this);
            }
            make_move(field, move.getToI(), move.getToJ(), player.getColor());
            if (player.getColor() == Cell.WHITE) {
                player = blackPlayer;
            } else {
                player = whiteplayer;
            }
        }
        System.out.println(announceWinner(field));
    }

    public void playerVsComputer(ComputerPlayer playerComp, IPlayer playerUser, Field field) {
        boolean no_white_moves = false;
        boolean no_black_moves = false;
        Move move;
        if (playerComp.getColor() == Cell.BLACK) {
            IPlayer player = playerComp;
            while (!(no_white_moves && no_black_moves)) {
                var moves = makeMoves(field, player.getColor());
                System.out.println(field.printField(moves));
                if (player.getColor() == Cell.BLACK) {
                    if (moves.isEmpty()) {
                        no_black_moves = true;
                        System.out.println("Нет черных ходов");
                        player = playerUser;
                        continue;
                    }
                    no_white_moves = false;
                    System.out.println("Ходят черные");
                    move = player.chooseMove(moves, field, this);
                } else {
                    if (moves.isEmpty()) {
                        no_white_moves = true;
                        System.out.println("Нет белых ходов");
                        player = playerComp;
                        continue;
                    }
                    no_white_moves = false;
                    System.out.println("Ходят белые");
                    move = player.chooseMove(moves, field, this);
                }
                make_move(field, move.getToI(), move.getToJ(), player.getColor());
                if (player.getColor() == Cell.WHITE) {
                    player = playerComp;
                } else {
                    player = playerUser;
                }
            }
        } else {
            IPlayer player = playerUser;
            while (!(no_white_moves && no_black_moves)) {
                var moves = makeMoves(field, player.getColor());
                System.out.println(field.printField(moves));
                if (player.getColor() == Cell.BLACK) {
                    if (moves.isEmpty()) {
                        no_black_moves = true;
                        System.out.println("Нет черных ходов");
                        player = playerComp;
                        continue;
                    }
                    no_white_moves = false;
                    System.out.println("Ходят черные");
                    move = player.chooseMove(moves, field, this);
                } else {
                    if (moves.isEmpty()) {
                        no_white_moves = true;
                        System.out.println("Нет белых ходов");
                        player = playerUser;
                        continue;
                    }
                    no_white_moves = false;
                    System.out.println("Ходят белые");
                    move = player.chooseMove(moves, field, this);
                }
                make_move(field, move.getToI(), move.getToJ(), player.getColor());
                if (player.getColor() == Cell.WHITE) {
                    player = playerUser;
                } else {
                    player = playerComp;
                }
            }
        }
        System.out.println(announceWinner(field));
    }

    public Integer getMaxResult() {
        return Collections.max(results);
    }
}
