package ui;

import model.Maze;

public class TextUI {
    private Maze maze;

    public TextUI(Maze maze) {
        this.maze = maze;
    }

    public void printMaze() {
        int width = maze.getWidth();;
        int length = maze.getLength();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {

            }
        }
    }
}
