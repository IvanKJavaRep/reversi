package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Выберите режим игры: " +
                    "легкий - введите 1, игрок против игрока - 2\n" +
                    "Если в режиме легкий хотите играть за черных, к 1 добавьте через пробел ключ b, если" +
                    "за белых - w\n" +
                    "Чтобы закончить сеанс, введите 3\n" +
                    "Чтобы вывести лучший результат за сессию введите 4\n");
            Game game = new Game();
            try {
                String input = br.readLine();
                if (input.equals("1 b")) {
                    game.firstScenario(results);
                } else if (input.equals("1 w")) {
                    game.secondScenario(results);
                } else if (input.equals("2")) {
                    game.thirdScenario(results);
                } else if (input.equals("3")) {
                    System.out.println("Конец игры");
                    break;
                } else if (input.equals("4")) {
                    if (results.isEmpty()) {
                        System.out.println("Игр еще не было\n");
                    } else {
                        System.out.println(Collections.max(results));
                    }
                } else {
                    System.out.println("Ошибка ввода, проверьте, что введенные ключи\n" +
                            "совпадают с правильными\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
