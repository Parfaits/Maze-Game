package ui;

import model.MazeElement;
import model.MazeGame;

import java.util.Scanner;

public class TextUI {

    // TODO: 2020-02-13 Make UI more gud

    private MazeGame game;
    private int width;
    private int length;
    private Scanner in = new Scanner(System.in);

    public TextUI(MazeGame game) {
        this.game = game;
        width = game.getWidth();
        length = game.getLength();
    }

    public void show() {
        while (!game.isGameEnd()) {
            while (!game.isPlayerDead()) {
                printMaze(game.getBoardMask(), game.getPlayerPosition(), game.getTomPosition(), game.getJoePosition(), game.getChadPosition());
                System.out.println("Cheese collected: " + game.getWins() + " of " + game.getMaxWins());
                System.out.print("Enter your move [WASD?]: ");
                String input = in.nextLine();
                String command = handleInvalidUserInput(input);
                while (!game.isMoveValidPlayer(command)) {
                    System.out.println("BRUH STOP");
                    System.out.print("Enter your move [WASD?]: ");
                    command = in.nextLine();
                    command = handleInvalidUserInput(command);
                }
                if (!(command.equalsIgnoreCase("c") || command.equalsIgnoreCase("m"))) {
                    game.handleMovementCommands(command);
                } else if (command.equalsIgnoreCase("c")) {
                    game.setMaxWins((byte) 1);
                } else if (command.equalsIgnoreCase("m")) {
                    printMaze(game.getBoard(), new int[]{}, new int[]{}, new int[]{}, new int[]{});
                }

                printMaze(game.getBoardMask(), game.getPlayerPosition(), game.getTomPosition(), game.getJoePosition(), game.getChadPosition());
                if (game.isPlayerWin()) {
                    System.out.println("SWAAAAG");
                    break;
                }
            }
            game.init(width, length);
        }
    }

    private String handleInvalidUserInput(String input) {
        String command = input;
        while (!(command.equalsIgnoreCase("w")
                || command.equalsIgnoreCase("a")
                || command.equalsIgnoreCase("s")
                || command.equalsIgnoreCase("d")
                || command.equalsIgnoreCase("c")
                || command.equalsIgnoreCase("m"))) {

            System.out.println("WTF");
            System.out.print("Enter your move [WASD?]: ");
            command = in.nextLine();
        }
        return command;
    }

    private void printMaze(MazeElement[][] board, int[] player, int[] tom, int[] joe, int[] chad) {
        int width = game.getWidth();;
        int length = game.getLength();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {

                if (player.length != 0 && tom.length != 0 && joe.length != 0 && chad.length != 0) {

                    if (i == player[1] && j == player[0]) {
                        System.out.print("@");
                    } else if (i == tom[1] && j == tom[0]) {
                        System.out.print("!");
                    } else if (i == joe[1] && j == joe[0]) {
                        System.out.print("!");
                    } else if (i == chad[1] && j == chad[0]) {
                        System.out.print("!");
                    } else if (board[i][j] == MazeElement.CHEESE) {
                        System.out.print("$");
                    } else if (board[i][j] == MazeElement.WALL) {
                        System.out.print("#");
                    } else if (board[i][j] == MazeElement.HIDDEN) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    if (board[i][j] == MazeElement.WALL) {
                        System.out.print("#");
                    } else if (board[i][j] == MazeElement.PLAYER) {
                        System.out.print("@");
                    } else if (board[i][j] == MazeElement.CAT) {
                        System.out.print("!");
                    } else if (board[i][j] == MazeElement.CHEESE) {
                        System.out.print("$");
                    } else if (board[i][j] == MazeElement.HIDDEN) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                }

//                if (board[i][j] == MazeElement.WALL) {
//                    System.out.print("#");
//                } else if (board[i][j] == MazeElement.PLAYER) {
//                    System.out.print("@");
//                } else if (board[i][j] == MazeElement.CAT) {
//                    System.out.print("!");
//                } else if (board[i][j] == MazeElement.CHEESE) {
//                    System.out.print("$");
//                } else if (board[i][j] == MazeElement.HIDDEN) {
//                    System.out.print(".");
//                } else {
//                    System.out.print(" ");
//                }
            }
            System.out.println();
        }
    }
}
