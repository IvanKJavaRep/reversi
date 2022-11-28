package com.company;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        while (true) {
            Field field = new Field();
            System.out.println("�������:\n1 ��� ���� ����� ������ ������\n" +
                    "2 ��� ���� � �����������\n" +
                    "3 ��� ������ ������������� �����\n" +
                    "x ��� ������ �� ����\n");
            Character gameType = game.chooseGameType();
            if (gameType == '1') {
                game.playerVsPlayer(new PersonPlayer(Cell.BLACK), new PersonPlayer(Cell.WHITE), field);
            } else if (gameType == '2') {
                ComputerPlayer player1 = game.chooseComputerColor();
                IPlayer player2 = PersonPlayer.getEnemy(player1);
                game.playerVsComputer(player1, player2, field);
            } else if (gameType == 'x') {
                System.out.println("���� ��������!");
                break;
            } else if (gameType == '3') {
                System.out.println(game.getMaxResult());
                continue;
            }

        }
    }
}
