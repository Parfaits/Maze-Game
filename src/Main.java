import model.MazeGame;
import ui.TextUI;

/**
 * Driver class
 * */

public class Main {
    public static void main(String[] args) {
        MazeGame game = new MazeGame();
        game.init(15, 20);
        TextUI textUI = new TextUI(game);
        textUI.show();
    }
}