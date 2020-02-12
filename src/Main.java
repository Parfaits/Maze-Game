import model.Maze;
import model.MazeGame;
import ui.TextUI;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
//        maze.printMaze();
        MazeGame game = new MazeGame(maze);
        TextUI textUI = new TextUI(maze);
        textUI.printMaze();
    }
}
