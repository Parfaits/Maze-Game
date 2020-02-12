package ui;

import model.Maze;
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
        while (game.isPlayerDead()) {
            
        }
    }

    public void printMaze() {
        int width = game.getWidth();;
        int length = game.getLength();
        MazeElement[][] board = game.getBoard();
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
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
