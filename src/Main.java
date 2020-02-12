import model.MazeGame;
import ui.TextUI;

public class Main {
    public static void main(String[] args) {
        MazeGame game = new MazeGame();
        game.init(10, 10);
        TextUI textUI = new TextUI(game);
        textUI.show();
    }
}