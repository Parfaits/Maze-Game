import model.Cat;
import model.Maze;
import model.MazeGame;
import model.Player;
import ui.TextUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
//        maze.printMaze();
        TextUI textUI = new TextUI(maze);
        textUI.printMaze();

//        // Sample Test code for the player class
//        Player player = new Player(0, 0, false);
//        player.printPlayerPos();
//
//        for (int i = 0; i < 5; i++){
//
//            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//            System.out.println("Enter keyInput");
//
//            String keyInput = myObj.nextLine();  // Read user input
//            player.movement(keyInput);
//            player.printPlayerPos();
//            System.out.println("keyInput is: " + keyInput);  // Output user input
//
//        }

        // sample test code for cat class.
        Cat cat1 = new Cat(0, 0);

        for (int i = 0; i < 5; i++){
            int randNum = cat1.generateRandomNum();
            cat1.movement(randNum);
            cat1.printCatPos();
        }

    }
}