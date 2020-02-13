package ui;

import model.MazeElement;
import model.MazeGame;

import java.util.Scanner;

public class TextUI {
    private MazeGame game;
    private Scanner in = new Scanner(System.in);

    public TextUI(MazeGame game) {
        this.game = game;
    }

    public void show() {
        while (!game.isGameEnd()) {
            while (!game.isPlayerDead()) {
                System.out.print("Enter your move [WASD?]: ");
                String input = in.nextLine();
                String command = handleInvalidUserInput(input);

                if (!game.isMoveValid(command)) {
                    System.out.println("BRUH");
                    command = handleInvalidUserInput(command);
                }
                if (!(command.equalsIgnoreCase("c") || command.equalsIgnoreCase("m"))) {
                    game.handleMovementCommands(command);
                } else if (command.equalsIgnoreCase("c")) {
                    game.setMaxWins((byte) 1);
                } else if (command.equalsIgnoreCase("m")) {
                    printMaze(game.getBoard());
                }
                printMaze(game.getBoardMask());
            }

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

            System.err.print("Enter your move [WASD?]: ");
            command = in.nextLine();
        }
        return command;
    }

    private void printMaze(MazeElement[][] board) {
        int width = game.getWidth();;
        int length = game.getLength();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
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
            System.out.println();
        }
    }
}
