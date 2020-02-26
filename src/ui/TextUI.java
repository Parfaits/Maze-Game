package ui;

import model.MazeElement;
import model.MazeGame;

import java.util.Scanner;

/**
 * Given a MazeGame, The class outputs the results of the game
 * to the user also handling user inputs.
 * */

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

        while (true) {
            game.updatePlayerInBoardMask();
            displayBoardMask();
            while (!game.isPlayerDead()) {
                System.out.print("Enter your move [WASD?]: ");
                String input = in.nextLine();
                String command = handleInvalidUserInput(input);
                System.out.println();

                handleUserCommands(command);


                if (game.isPlayerWin()) {
                    System.out.println("Cheese eaten\nRound win.");
                    displayBoard();
                    break;
                }
            }
            if (game.isPlayerDead()) {
                System.out.println("Cat ate the mouse (you)\nYOU LOSE REKT GET GUD~!!~!!!!");
                displayBoard();
                break;
            }
            if (game.isGameEnd()) {
                System.out.println("Congratulations you won!");
                displayBoard();
                break;
            }
            game.init(width, length);
        }
    }

    private void displayBoard() {
        printMaze(game.getBoard(), new int[]{}, new int[]{}, new int[]{}, new int[]{});
        System.out.println("Cheese collected: " + game.getWins() + " of " + game.getMaxWins());
    }

    private void displayBoardMask() {
        printMaze(game.getBoardMask(), game.getPlayerPosition(), game.getTomPosition(), game.getJoePosition(), game.getChadPosition());
        System.out.println("Cheese collected: " + game.getWins() + " of " + game.getMaxWins());
    }

    private void handleUserCommands(String command) {
        switch (command) {
            case "w": case "a": case "s": case "d":
                game.handleMovementCommands(command);
                displayBoardMask();
                break;

            case "c":
                game.setMaxWins((byte) 1);
                break;

            case "m":
                displayBoard();
                break;

            case "?":
                displayInstructions();
                break;

            default:
                System.err.println("Unexpected command.");
                break;
        }
    }

    private String handleInvalidUserInput(String input) {
        String command = input;
        while (!(game.isMoveValidPlayer(command)
                && (command.equalsIgnoreCase("w")
                || command.equalsIgnoreCase("a")
                || command.equalsIgnoreCase("s")
                || command.equalsIgnoreCase("d"))
                || command.equalsIgnoreCase("c")
                || command.equalsIgnoreCase("m")
                || command.equalsIgnoreCase("?"))) {

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
        int[] cheesePos = MazeGame.getCheesePosition();
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
                    } else if (i == cheesePos[1] && j == cheesePos[0]) {
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
            }
            System.out.println();
        }
    }

    private void displayTitle(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Cat and Mouse Maze Adventure!");
        System.out.println("by Johnny D and Fazal W");
        System.out.println("----------------------------------------");
        System.out.println();
    }

    private void displayInstructions(){
        System.out.println("DIRECTIONS:");
        System.out.println("        Find " + game.getMaxWins() + " cheese before a cat eats you!");

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
