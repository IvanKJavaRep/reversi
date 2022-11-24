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
            System.out.println("�������� ����� ����: " +
                    "������ - ������� 1, ����� ������ ������ - 2\n" +
                    "���� � ������ ������ ������ ������ �� ������, � 1 �������� ����� ������ ���� b, ����" +
                    "�� ����� - w\n" +
                    "����� ��������� �����, ������� 3\n" +
                    "����� ������� ������ ��������� �� ������ ������� 4\n");
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
                    System.out.println("����� ����");
                    break;
                } else if (input.equals("4")) {
                    if (results.isEmpty()) {
                        System.out.println("��� ��� �� ����\n");
                    } else {
                        System.out.println(Collections.max(results));
                    }
                } else {
                    System.out.println("������ �����, ���������, ��� ��������� �����\n" +
                            "��������� � �����������\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
