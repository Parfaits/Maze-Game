import model.Cat;
import model.Maze;
import model.MazeGame;
import model.Player;
import ui.TextUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MazeGame game = new MazeGame();
        game.init(10, 10);
        TextUI textUI = new TextUI(game);
        textUI.show();
    }
}