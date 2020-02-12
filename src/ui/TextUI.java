package ui;

import model.Maze;
import model.MazeElement;

public class TextUI {
    private Maze maze;

    public TextUI(Maze maze) {
        this.maze = maze;
    }

    public void printMaze() {
        int width = maze.getWidth();;
        int length = maze.getLength();
        MazeElement[][] board = maze.getMaze();
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
