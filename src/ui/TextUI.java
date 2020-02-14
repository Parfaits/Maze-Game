package ui;

import model.MazeElement;
import model.MazeGame;

import java.util.Scanner;

public class TextUI {

    // TODO: 2020-02-13 Make UI more gud
    // FIXME: 2020-02-13 UI is TRASH // Fixed UI

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
        displayTitle();
        displayInstructions();

        while (!game.isGameEnd()) {
            game.updatePlayerInBoardMask();
            printMaze(game.getBoardMask(), game.getPlayerPosition(), game.getTomPosition(), game.getJoePosition(), game.getChadPosition());
            System.out.println("Cheese collected: " + game.getWins() + " of " + game.getMaxWins());
            while (!game.isPlayerDead()) {
                System.out.print("Enter your move [WASD?]: ");
                String input = in.nextLine();
                String command = handleInvalidUserInput(input);
                System.out.println();

                if (!(command.equalsIgnoreCase("c") || command.equalsIgnoreCase("m"))) {
                    game.handleMovementCommands(command);
                    printMaze(game.getBoardMask(), game.getPlayerPosition(), game.getTomPosition(), game.getJoePosition(), game.getChadPosition());
                } else if (command.equalsIgnoreCase("c")) {
                    game.setMaxWins((byte) 1);
                } else if (command.equalsIgnoreCase("m")) {
                    printMaze(game.getBoard(), new int[]{}, new int[]{}, new int[]{}, new int[]{});

                }


                if (game.isPlayerWin()) {
                    System.out.println("Round Won.");
                    break;
                }

            }
            game.init(width, length);
        }
        System.out.println("Congratulations you won!");
        printMaze(game.getBoard(), new int[]{}, new int[]{}, new int[]{}, new int[]{});
        System.out.println("Cheese collected: " + game.getWins() + " of " + game.getMaxWins());
    }

    private String handleInvalidUserInput(String input) {
        String command = input;
        while (!(game.isMoveValidPlayer(command)
                && (command.equalsIgnoreCase("w")
                || command.equalsIgnoreCase("a")
                || command.equalsIgnoreCase("s")
                || command.equalsIgnoreCase("d"))
                || command.equalsIgnoreCase("c")
                || command.equalsIgnoreCase("m"))) {

            if (!(command.equalsIgnoreCase("w")
                    || command.equalsIgnoreCase("a")
                    || command.equalsIgnoreCase("s")
                    || command.equalsIgnoreCase("d"))) {

                System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
            } else {
                System.out.println("Invalid move: you cannot move through walls!");

            }
            System.out.print("Enter your move [WASD?]: ");
            command = in.nextLine();
        }

        return command;
    }

    private void printMaze(MazeElement[][] board, int[] player, int[] tom, int[] joe, int[] chad) {
        System.out.println("Maze:");
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

    void displayTitle(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Cat and Mouse Maze Adventure!");
        System.out.println("by Johnny D and Fazal W");
        System.out.println("----------------------------------------");
        System.out.println();
    }

    void displayInstructions(){
        System.out.println("DIRECTIONS:");
        System.out.println("        Find 5 cheese before a cat eats you!");

        System.out.println("LEGEND:");
        System.out.println("        #: Wall");
        System.out.println("        @: You (a mouse)");
        System.out.println("        !: Cat");
        System.out.println("        $: Cheese");
        System.out.println("        .: Unexplored space");

        System.out.println("MOVES:");
        System.out.println("        Use W (up), A (left), S (down) and D (right) to move.");
        System.out.println("        (You must press enter after each move).");
        System.out.println();
    }


}
